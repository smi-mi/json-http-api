package com.example.demo.services.impl;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.StatisticService;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Override
    public Iterable<StatusChange> get() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<StatusChange> get(Status status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<StatusChange> get(Long timestamp) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<StatusChange> get(Status status, Long timestamp) {
        throw new UnsupportedOperationException();
    }
}
