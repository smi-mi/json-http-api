package com.example.demo.services;

import com.example.demo.repositories.projections.ProfileOnly;
import org.springframework.lang.Nullable;

public interface StatisticService {

    Iterable<ProfileOnly> get(@Nullable String statusValue, @Nullable Long timestamp);
}
