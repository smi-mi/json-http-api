package com.example.demo.services.impl;

import com.example.demo.entities.Status;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.projections.ProfileOnly;
import com.example.demo.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatusRepository statusRepository;

    private final StatusChangeRepository statusChangeRepository;

    @Override
    public Iterable<ProfileOnly> get(String statusValue, Long timestamp) { // TODO return only profiles
        if (statusValue == null && timestamp == null) {
            //return statusChangeRepository.findDistinctAll();
            return statusChangeRepository.findDistinctByTimestampGreaterThan(0L); // HACK
        }
        if (statusValue == null) {
            return statusChangeRepository.findDistinctByTimestampGreaterThan(timestamp);
        }

        Optional<Status> statusOptional = statusRepository.findByValue(statusValue);
        Status status = statusOptional.orElseThrow(
                () -> new NoSuchElementException("Impossible status")
        );

        if (timestamp == null) {
            return statusChangeRepository.findDistinctByProfileStatusValueLike(statusValue);
        }
        return statusChangeRepository.
                findDistinctByTimestampGreaterThanAndProfileStatusValueLike(
                        timestamp,
                        statusValue
                );
    }
}
