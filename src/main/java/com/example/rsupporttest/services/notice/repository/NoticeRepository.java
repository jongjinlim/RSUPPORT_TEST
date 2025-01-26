package com.example.rsupporttest.services.notice.repository;

import com.example.rsupporttest.services.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, String> {
}
