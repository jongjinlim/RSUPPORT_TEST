package com.example.rsupporttest.services.notice.domain;

import com.example.rsupporttest.services.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Data
@Comment("공지사항")
@Table(name = "R_NOTICE")
@NoArgsConstructor(access = PROTECTED)
public class Notice extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = IDENTITY)
	@Comment("seq")
	private Long id;

	@Column(name = "title")
	@Comment("제목")
	private String title;

	@Column(name = "contents")
	@Comment("내용")
	private String contents;

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
			String createdBy
	) {
		this.title = title;
		this.contents = contents;
		this.createdBy = createdBy;
	}

	public Notice toNotice() {
		return Notice.builder()
				.title(this.title)
				.contents(this.contents)
				.createdBy(this.createdBy)
				.build();
	}

}
