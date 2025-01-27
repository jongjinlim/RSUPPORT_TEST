package com.example.rsupporttest.services.attach.domain;

import com.example.rsupporttest.services.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Comment("공지사항 첨부파일")
@Table(name = "attach")
@NoArgsConstructor(access = PROTECTED)
public class Attach extends BaseEntity {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "notice_id")
	private Long noticeId;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_url")
	private String fileUrl;

	@Builder
	private Attach(
			Long noticeId,
			String fileName,
			String fileUrl
	) {
		this.noticeId = noticeId;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
	}

	public Attach toAttach() {
		return Attach.builder()
				.noticeId(this.noticeId)
				.fileName(this.fileName)
				.fileUrl(this.fileUrl)
				.build();
	}
}
