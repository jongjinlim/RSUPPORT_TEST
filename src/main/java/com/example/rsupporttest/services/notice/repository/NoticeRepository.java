package com.example.rsupporttest.services.notice.repository;

import com.example.rsupporttest.services.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

	Optional<Notice> findById(Long id);
}
