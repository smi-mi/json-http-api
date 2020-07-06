package com.example.demo.repositories;

import com.example.demo.entities.StatusChange;
import org.springframework.data.repository.CrudRepository;

public interface StatusChangeRepository extends CrudRepository<StatusChange, Integer> {
    Iterable<StatusChange> findDistinctProfileByTimestampGreaterThan(Long time);
}
