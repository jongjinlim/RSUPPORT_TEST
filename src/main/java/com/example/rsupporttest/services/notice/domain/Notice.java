package com.example.rsupporttest.services.notice.domain;

import com.example.rsupporttest.services.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Comment("공지사항")
@Table(name = "R_NOTICE")
@NoArgsConstructor(access = PROTECTED)
public class Notice extends BaseEntity {

	// 제목, 내용, 공지 시작일시, 공지 종료일시, 첨부파일 (여러개)
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = IDENTITY)
	@Comment("seq")
	private Long id;

	@Column(name = "title")
	@Comment("제목")
	private String title;

	@Column(name = "contents")
	@Lob
	@Comment("내용")
	private String contents;

	@Column(name = "start_date")
	@Comment("공지 시작일시")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	@Comment("공지 종료일시")
	private LocalDateTime endDate;

	@Column(name = "createdBy")
	@Comment("등록자")
	private String createdBy;

	@Column(name = "viewCount")
	@Comment("조회수")
	private Integer viewCount;

	@Builder
	private Notice(
			String title,
			String contents,
			LocalDateTime startDate,
			LocalDateTime endDate,
			String createdBy
	) {
		this.title = title;
		this.contents = contents;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
	}

	public Notice toNotice() {
		return Notice.builder()
				.title(this.title)
				.contents(this.contents)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.createdBy(this.createdBy)
				.build();
	}

}
