package com.example.demo.repositories;

import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.projections.ProfileOnly;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface StatusChangeRepository extends CrudRepository<StatusChange, Integer> {

    Iterable<ProfileOnly> findDistinctByTimestampGreaterThanAndProfileStatusLike(
            @NonNull Long time,
            @NonNull Status status
    );

    Iterable<ProfileOnly> findDistinctByTimestampGreaterThan(@NonNull Long time);

    Iterable<ProfileOnly> findDistinctByProfileStatusLike(@NonNull Status status);

    //Iterable<ProfileOnly> findDistinctAll();
}
