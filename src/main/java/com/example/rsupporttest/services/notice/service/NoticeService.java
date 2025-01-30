package com.example.rsupporttest.services.notice.service;

import com.example.rsupporttest.error.CoreException;
import com.example.rsupporttest.error.ErrorType;
import com.example.rsupporttest.services.attach.domain.Attach;
import com.example.rsupporttest.services.attach.repository.AttachRepository;
import com.example.rsupporttest.services.attach.service.AttachService;
import com.example.rsupporttest.services.notice.domain.CreateNotice;
import com.example.rsupporttest.services.notice.domain.Notice;
import com.example.rsupporttest.services.notice.domain.UpdateNotice;
import com.example.rsupporttest.services.notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NoticeService {
	private final NoticeRepository noticeRepository;
	private final AttachRepository attachRepository;
	private final AttachService attachService;

	public NoticeService(NoticeRepository noticeRepository, S3Client s3Client,
						 AttachRepository attachRepository, AttachService attachService) {
		this.noticeRepository = noticeRepository;
		this.attachService = attachService;
		this.attachRepository = attachRepository;
	}

	/**
	 * 공지사항 등록
	 * @param createNotice
	 */
	@Transactional
	public Long createNotice(CreateNotice createNotice, List<MultipartFile> files) throws IOException {

		// 1. 공지사항 저장
		Notice notice = Notice.builder()
				.title(createNotice.title())
				.contents(createNotice.contents())
				.startDate(createNotice.startDate())
				.endDate(createNotice.endDate())
				.createdBy(createNotice.createdBy())
				.build();
		Notice savedNotice = this.noticeRepository.save(notice);

		// 2. 첨부파일 저장
		if(files != null && !files.isEmpty()) {
			this.uploadFilesAsync(savedNotice, files);
		}

		return savedNotice.getId();
	}

	/**
	 * 공지사항 수정
	 * @param updateNotice
	 * @param newFiles
	 * @param deleteFileIds
	 * @return
	 */
	@Transactional
	public Notice updateNotice(UpdateNotice updateNotice, List<MultipartFile> newFiles, List<Long> deleteFileIds) {
		Notice notice = getNotice(updateNotice.id());
		notice.updateNoticeData(updateNotice);
		Notice updatedNotice = this.noticeRepository.save(notice);

		if (deleteFileIds != null && !deleteFileIds.isEmpty()) {
			List<Attach> attachmentsToDelete = attachRepository.findAllById(deleteFileIds);
			attachmentsToDelete.forEach(attach -> attachService.deleteFileFromS3(attach.getFileUrl()));
			attachRepository.deleteAllInBatch(attachmentsToDelete);
		}

		if (newFiles != null && !newFiles.isEmpty()) {
			uploadFilesAsync(notice, newFiles);
		}

		return updatedNotice;
	}

	/**
	 * 공지사항 삭제
	 * @param id
	 */
	@Transactional
	public void deleteNotice(Long id) {
		Notice notice = getNotice(id);

		List<Attach> attachList = attachRepository.findByNoticeId(id);
		attachList.forEach(attach -> attachService.deleteFileFromS3(attach.getFileUrl()));
		attachRepository.deleteAllInBatch(attachList);

		this.noticeRepository.delete(notice);
	}

	/**
	 * 공지사항 단건 조회
	 * @param id
	 * @return
	 */
	@Cacheable(value = "noticeCache", key = "#id")
	public Notice getNotice(Long id) {
		Notice notice = noticeRepository.findById(id).orElseThrow(() -> CoreException.of(ErrorType.DEFAULT_ERROR, "공지사항이 존재하지 않습니다."));
		return notice;
	}

	@Transactional
	public void incrementViewCount(Long id) {
		this.noticeRepository.incrementViewCount(id);
	}

	@Async
	public void uploadFilesAsync(Notice notice, List<MultipartFile> files) {
		List<Attach> attachments = files.stream().map(file -> {
			String fileUrl = attachService.uploadFileToS3(file);
			return new Attach(notice.getId(), file.getOriginalFilename(), fileUrl);
		}).collect(Collectors.toList());
		attachRepository.saveAll(attachments);
	}
}
