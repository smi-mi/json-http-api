package com.example.demo.repositories;

import com.example.demo.entities.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, String> {
}
