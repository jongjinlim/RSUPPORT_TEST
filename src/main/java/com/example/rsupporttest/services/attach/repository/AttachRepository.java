package com.example.rsupporttest.services.attach.repository;

import com.example.rsupporttest.services.attach.domain.Attach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepository extends JpaRepository<Attach, Long> {
}
