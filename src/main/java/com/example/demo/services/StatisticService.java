package com.example.demo.services;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;

public interface StatisticService {

    Iterable<StatusChange> get();

    Iterable<StatusChange> get(Status status);

    Iterable<StatusChange> get(Long timestamp);

    Iterable<StatusChange> get(Status status, Long timestamp);
}
