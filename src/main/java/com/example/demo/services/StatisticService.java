package com.example.demo.services;

import com.example.demo.entities.Profile;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.projections.StatusChangeProjectProfile;
import org.springframework.lang.Nullable;

public interface StatisticService {

    Iterable<StatusChangeProjectProfile> get(@Nullable String statusValue, @Nullable Long timestamp);
}
