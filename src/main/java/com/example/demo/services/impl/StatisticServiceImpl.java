package com.example.demo.services.impl;

import com.example.demo.entities.Profile;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.projections.StatusChangeProjectProfile;
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
    public Iterable<StatusChangeProjectProfile> get(String statusValue, Long timestamp) {
        if (statusValue == null && timestamp == null) {
            // TODO find out how to select distinct profiles from all rows
            //return statusChangeRepository.findDistinctProfilesAll();
        }
        if (statusValue == null) {
            return statusChangeRepository.findDistinctByTimestampGreaterThan(timestamp);
        }

        Optional<Status> statusOptional = statusRepository.findByValue(statusValue);
        if (statusOptional.isEmpty()) {
            throw new NoSuchElementException("Impossible status");
        }
        Status status = statusOptional.get();

        if (timestamp == null) {
            return statusChangeRepository.findDistinctByProfileStatusLike(status);
        }
        return statusChangeRepository.
                findDistinctByTimestampGreaterThanAndProfileStatusLike(
                        timestamp,
                        status
                );
    }
}
