package com.challenge.application.service;

import com.challenge.application.entity.History;
import com.challenge.application.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public Page<History> getAllHistory(final Integer pageSize, final Integer pageNumber, final String sortBy) {
        int size = pageSize != null && pageSize != 0 ? pageSize : 10;
        int page = pageNumber != null ? pageNumber : 0;
        String sort = sortBy != null ? sortBy : "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<History> history = historyRepository.findAll(pageable);

        return history;
    }

    @Override
    public History saveHistory(final String url) {
        History history = new History();
        history.setUrl(url);
        return historyRepository.save(history);
    }
}
