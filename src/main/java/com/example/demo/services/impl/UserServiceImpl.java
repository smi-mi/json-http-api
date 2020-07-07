package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.ProfileRepository;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final StatusChangeRepository statusChangeRepository;

    @Override
    public Person addUser(Person person) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Person> getUserPersonalData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatusChange changeUserStatus(Status newStatus) {
        throw new UnsupportedOperationException();
    }
}
