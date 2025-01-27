package com.example.rsupporttest.controller;

import com.example.rsupporttest.services.notice.domain.NoticeRequest;
import com.example.rsupporttest.services.notice.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	// todo 공지사항 등록, 수정, 삭제, 조회 API를 구현한다.
	@PostMapping("/create")
	public ResponseEntity<String> createNotice(@RequestBody NoticeRequest noticeRequest) {
		this.noticeService.createNotice(noticeRequest.toCreateNotice());
		return ResponseEntity.ok("공지사항 등록 완료");
	}

	@PostMapping("/update")
	public String updateNotice() {
		return "";
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
