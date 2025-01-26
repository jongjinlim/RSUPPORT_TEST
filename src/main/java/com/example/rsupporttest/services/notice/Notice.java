package com.example.rsupporttest.services.notice;

import com.example.rsupporttest.services.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Data
public class Notice extends BaseEntity {

	@Id
	@Column(name = "id")
	@Comment("seq")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "contents")
	private String contents;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "viewCount")
	private String viewCount;

}
