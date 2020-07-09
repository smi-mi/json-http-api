package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.entities.Profile;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.ProfileRepository;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PersonRepository personRepository;

    private final ProfileRepository profileRepository;

    private final StatusRepository statusRepository;

    private final StatusChangeRepository statusChangeRepository;

    @Override
    public Person addUser(@NonNull Person person) {
        personRepository.save(person);
        Profile profile = new Profile(person);
        profileRepository.save(profile);
        return person;
    }

    @Override
    public Person getUserPersonalData(@NonNull Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        return personOptional.orElseThrow(
                () -> new NoSuchElementException("No user with such id")
        );
    }

    @Override
    public StatusChange changeUserStatus(@NonNull Integer id, @NonNull String newStatusValue) {
        Optional<Profile> profileOptional = profileRepository.findByPersonId(id);
        Profile profile = profileOptional.orElseThrow(
                () -> new NoSuchElementException("No user with such id")
        );

        Optional<Status> newStatusOptional = statusRepository.findByValue(newStatusValue);
        Status newStatus = newStatusOptional.orElseThrow(
                () -> new NoSuchElementException("Impossible status")
        );

        Status lastStatus = profile.getStatus();
        profile.setStatus(newStatus);
        profileRepository.save(profile);

        StatusChange statusChange = new StatusChange(
                profile,
                System.currentTimeMillis(),
                lastStatus,
                newStatus
        );
        statusChangeRepository.save(statusChange);
        return statusChange;
    }
}
