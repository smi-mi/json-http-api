package com.example.demo.repositories;

import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.projections.ProfileOnly;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface StatusChangeRepository extends CrudRepository<StatusChange, Integer> {

    Iterable<ProfileOnly> findDistinctByTimestampGreaterThanAndProfileStatusValueLike(
            @NonNull Long time,
            @NonNull String statusValue
    );

    Iterable<ProfileOnly> findDistinctByTimestampGreaterThan(@NonNull Long time);

    Iterable<ProfileOnly> findDistinctByProfileStatusValueLike(@NonNull String statusValue);

    //Iterable<ProfileOnly> findDistinctAll();
}
