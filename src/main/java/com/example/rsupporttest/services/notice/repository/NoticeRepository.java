package com.example.rsupporttest.services.notice.repository;

import com.example.rsupporttest.services.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

	Optional<Notice> findById(Long id);

	@Modifying
	@Query(value = "UPDATE R_NOTICE A SET A.view_count = A.view_count + 1 WHERE A.id = :id", nativeQuery = true)
	void incrementViewCount(Long id);
}
