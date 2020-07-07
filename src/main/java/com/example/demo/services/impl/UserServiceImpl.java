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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final StatusRepository statusRepository;
    @Autowired
    private final StatusChangeRepository statusChangeRepository;

    @Override
    public Person addUser(@NonNull Person person) {
        personRepository.save(person);
        Profile profile = new Profile(person, new Status());
        profileRepository.save(profile);
        return person;
    }

    @Override
    public Optional<Person> getUserPersonalData(@NonNull Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public StatusChange changeUserStatus(@NonNull Integer id, @NonNull String newStatusValue) throws NoSuchElementException {
        Optional<Profile> profileOptional = profileRepository.findByPersonId(id);
        if (profileOptional.isEmpty()) {
            throw new NoSuchElementException("No user with such id");
        }
        Optional<Status> newStatusOptional = statusRepository.findByValue(newStatusValue);
        if (newStatusOptional.isEmpty()) {
            throw new NoSuchElementException("Impossible status");
        }

        Profile profile = profileOptional.get();
        Status lastStatus = profile.getStatus();
        Status newStatus = newStatusOptional.get();
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
