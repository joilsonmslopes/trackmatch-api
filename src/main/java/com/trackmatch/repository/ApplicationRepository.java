package com.trackmatch.repository;

import com.trackmatch.domain.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
