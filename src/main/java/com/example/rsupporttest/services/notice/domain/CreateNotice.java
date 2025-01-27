package com.example.rsupporttest.services.notice.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateNotice(
		 String title,
		 String contents,
		 LocalDateTime startDate,
		 LocalDateTime endDate,
		 String createdBy
) {}
