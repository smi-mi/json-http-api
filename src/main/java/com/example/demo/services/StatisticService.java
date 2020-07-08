package com.example.demo.services;

import com.example.demo.repositories.projections.StatusChangeProjectProfile;
import org.springframework.lang.Nullable;

public interface StatisticService {

    Iterable<StatusChangeProjectProfile> get(@Nullable String statusValue, @Nullable Long timestamp);
}
