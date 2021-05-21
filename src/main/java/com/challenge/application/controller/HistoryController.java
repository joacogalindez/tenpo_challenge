package com.challenge.application.controller;

import com.challenge.application.entity.History;
import com.challenge.application.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping("")
    public ResponseEntity<Page<History>> getHistory(@RequestParam(required = false) Integer size,
                                                    @RequestParam(required = false) Integer page_number,
                                                    @RequestParam(required = false) String sort_by) {
        try {
            Page<History> history = historyService.getAllHistory(size, page_number, sort_by);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
