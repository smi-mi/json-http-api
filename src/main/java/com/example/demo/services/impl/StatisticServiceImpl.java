package com.example.demo.services.impl;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private final StatusRepository statusRepository;
    @Autowired
    private final StatusChangeRepository statusChangeRepository;

    @Override
    public Iterable<StatusChange> get(String statusValue, Long timestamp) {
        if (statusValue == null && timestamp == null) {
            return statusChangeRepository.findDistinctProfile();
        }
        if (statusValue == null) {
            return statusChangeRepository.findDistinctProfileByTimestampGreaterThan(timestamp);
        }

        Optional<Status> statusOptional = statusRepository.findByValue(statusValue);
        if (statusOptional.isEmpty()) {
            throw new NoSuchElementException("Impossible status");
        }
        Status status = statusOptional.get();

        if (timestamp == null) {
            return statusChangeRepository.findDistinctProfileByProfileStatusLike(status);
        }
        return statusChangeRepository.
                findDistinctProfileByTimestampGreaterThanAndProfileStatusLike(
                        timestamp,
                        status
                );
    }
}
