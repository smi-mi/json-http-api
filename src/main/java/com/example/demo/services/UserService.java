package com.example.demo.services;

import com.example.demo.entities.Person;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;

import java.util.Optional;

public interface UserService {

    Person addUser(Person person);

    Optional<Person> getUserPersonalData();

    StatusChange changeUserStatus(Status newStatus);
}
