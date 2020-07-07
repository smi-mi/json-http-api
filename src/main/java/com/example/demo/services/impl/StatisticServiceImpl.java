package com.example.demo.services.impl;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private final StatusChangeRepository statusChangeRepository;

    @Override
    public Iterable<StatusChange> get(String statusValue, Long timestamp) {
        throw new UnsupportedOperationException();
    }
}
