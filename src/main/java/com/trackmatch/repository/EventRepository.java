package com.trackmatch.repository;

import com.trackmatch.domain.entities.EventEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EventRepository extends PagingAndSortingRepository<EventEntity, Long> {
    Optional<EventEntity> findById(Long id);

    EventEntity save(EventEntity eventEntity);

    void deleteById(Long id);
}
