package com.example.rsupporttest.services.notice.domain;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NoticeRequest(
		@NotNull String title,
		@NotNull String contents,
		@NotNull LocalDateTime startDate,
		@NotNull LocalDateTime endDate,
		@NotNull String createdBy
) {

	public CreateNotice toCreateNotice() {
		return CreateNotice.builder()
				.title(this.title)
				.contents(this.contents)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.createdBy(this.createdBy)
				.build();
	}
}
