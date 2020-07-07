package com.example.demo.repositories;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import org.springframework.data.repository.CrudRepository;

public interface StatusChangeRepository extends CrudRepository<StatusChange, Integer> {

    Iterable<StatusChange> findDistinctProfileByTimestampGreaterThanAndProfileStatusLike(
            Long time,
            Status status
    );

    Iterable<StatusChange> findDistinctProfileByTimestampGreaterThan(Long time);

    Iterable<StatusChange> findDistinctProfileByProfileStatusLike(Status status);

    Iterable<StatusChange> findDistinctProfile();
}
