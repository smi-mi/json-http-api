package com.example.demo.services.impl;

import com.example.demo.entities.Person;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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
