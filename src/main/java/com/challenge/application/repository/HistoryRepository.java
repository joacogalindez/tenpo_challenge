package com.challenge.application.repository;

import com.challenge.application.entity.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends PagingAndSortingRepository<History, Integer> {
}
