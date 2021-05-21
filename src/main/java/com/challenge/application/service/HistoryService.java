package com.challenge.application.service;

import com.challenge.application.entity.History;
import org.springframework.data.domain.Page;

public interface HistoryService {

    Page<History> getAllHistory(final Integer pageSize, final Integer pageNumber, final String sortBy);

    History saveHistory(final String url);

}
