package com.example.demo.repositories;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.projections.ProfileOnly;
import org.springframework.data.repository.CrudRepository;

public interface StatusChangeRepository extends CrudRepository<StatusChange, Integer> {

    Iterable<ProfileOnly> findDistinctByTimestampGreaterThanAndProfileStatusLike(
            Long time,
            Status status
    );

    Iterable<ProfileOnly> findDistinctByTimestampGreaterThan(Long time);

    Iterable<ProfileOnly> findDistinctByProfileStatusLike(Status status);

    //Iterable<ProfileOnly> findDistinctAll();
}
