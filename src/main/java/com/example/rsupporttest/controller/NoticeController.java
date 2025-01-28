package com.example.rsupporttest.controller;

import com.example.rsupporttest.services.notice.domain.NoticeRequest;
import com.example.rsupporttest.services.notice.domain.NoticeResponse;
import com.example.rsupporttest.services.notice.service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	// todo 공지사항 등록, 수정, 삭제, 조회 API를 구현한다.

	/**
	 * 공지사항 등록
	 * @param noticeRequest
	 * @param files
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createNotice(@Valid @RequestBody NoticeRequest noticeRequest,
											   @RequestParam List<MultipartFile> files) {
		this.noticeService.createNotice(noticeRequest.toCreateNotice(), files);
		return ResponseEntity.ok("공지사항 등록 완료");
	}

	/**
	 * 공지사항 수정
	 * @param id
	 * @param noticeRequest
	 * @return
	 */
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateNotice(@PathVariable Long id, @Valid @RequestBody NoticeRequest noticeRequest) {
		return ResponseEntity.ok(this.noticeService.updateNotice(noticeRequest.toUpdateNotice(id)));
	}

	@DeleteMapping("/delete")
	public String deleteNotice() {
		return "";
	}

//	@GetMapping("/list")
//	public ResponseEntity<List<Notice>> getNoticeList() {
//		return new ResponseEntity<>(noticeService.getNoticeList(userId), HttpStatus.OK);
//	}


}
