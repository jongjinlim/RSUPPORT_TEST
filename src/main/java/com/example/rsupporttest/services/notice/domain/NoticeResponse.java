package com.example.rsupporttest.services.notice.domain;

import java.time.LocalDateTime;

public record NoticeResponse(
		String title,
		String contents,
		LocalDateTime startDate,
		LocalDateTime endDate,
		String createdBy
) { }
