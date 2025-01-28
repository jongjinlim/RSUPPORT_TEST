package com.example.rsupporttest.services.notice.service;

import com.example.rsupporttest.error.CoreException;
import com.example.rsupporttest.error.ErrorType;
import com.example.rsupporttest.services.attach.domain.Attach;
import com.example.rsupporttest.services.notice.domain.CreateNotice;
import com.example.rsupporttest.services.notice.domain.Notice;
import com.example.rsupporttest.services.notice.domain.NoticeResponse;
import com.example.rsupporttest.services.notice.domain.UpdateNotice;
import com.example.rsupporttest.services.notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;

	public NoticeService(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}

	/**
	 * 공지사항 등록
	 * @param createNotice
	 */
	@Transactional
	public void createNotice(CreateNotice createNotice, List<MultipartFile> files) {
		Notice notice = Notice.builder()
				.title(createNotice.title())
				.contents(createNotice.contents())
				.startDate(createNotice.startDate())
				.endDate(createNotice.endDate())
				.createdBy(createNotice.createdBy())
				.build();

		// todo file upload
		List<Attach> attachments = new ArrayList<>();
		for (MultipartFile file : files) {

		}
		this.noticeRepository.save(notice);
	}

	@Transactional
	public NoticeResponse updateNotice(UpdateNotice updateNotice) {

		Notice notice = getNotice(updateNotice.id());
		this.noticeRepository.save(notice);
		return null;
	}

	public void deleteNotice() {

	}

	/**
	 * 공지사항 단건 조회
	 * @param id
	 * @return
	 */
	public Notice getNotice(Long id) {
		// todo 조회수 등록
		Notice notice = noticeRepository.findById(id).orElseThrow(() -> CoreException.of(ErrorType.DEFAULT_ERROR, "공지사항이 존재하지 않습니다."));
		return notice;
	}
}
