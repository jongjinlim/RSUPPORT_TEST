package com.example.rsupporttest.services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name = "reg_date", updatable = false)
	@Comment("등록일시")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "mdf_date")
	@Comment("수정일시")
    private LocalDateTime lastModifiedAt;
}
