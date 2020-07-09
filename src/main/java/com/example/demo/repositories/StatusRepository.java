package com.example.demo.repositories;

import com.example.demo.entities.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Integer> {

    Optional<Status> findByValue(String value);
}
