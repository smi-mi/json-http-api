package com.example.demo.services;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import org.springframework.lang.Nullable;

public interface StatisticService {

    Iterable<StatusChange> get(@Nullable Status status, @Nullable Long timestamp);
}
