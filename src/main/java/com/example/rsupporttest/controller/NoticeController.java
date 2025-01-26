package com.example.rsupporttest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	// todo 공지사항 등록, 수정, 삭제, 조회 API를 구현한다.
	@PostMapping("/create")
	public String createNotice() {
		return "";
	}

	@PostMapping("/update")
	public String updateNotice() {
		return "";
	}

	@DeleteMapping("/delete")
	public String deleteNotice() {
		return "";
	}

	@GetMapping("/list")
	public String getNoticeList() {
		return "";
	}


}
