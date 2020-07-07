package com.example.demo.repositories;

import com.example.demo.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    Optional<Profile> findByPersonId(Integer id);
}
