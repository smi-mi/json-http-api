package com.example.demo.repositories;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.projections.StatusChangeProjectProfile;
import org.springframework.data.repository.CrudRepository;

public interface StatusChangeRepository extends CrudRepository<StatusChange, Integer> {

    Iterable<StatusChangeProjectProfile> findDistinctByTimestampGreaterThanAndProfileStatusLike(
            Long time,
            Status status
    );

    Iterable<StatusChangeProjectProfile> findDistinctByTimestampGreaterThan(Long time);

    Iterable<StatusChangeProjectProfile> findDistinctByProfileStatusLike(Status status);

    //Iterable<StatusChangeProjectProfile> findDistinctAll();
}
