package com.example.demo.controllers;

import com.example.demo.entities.Person;
import com.example.demo.entities.Profile;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Person addUser(@RequestBody Person person) {
        return userService.addUser(person);
    }

    @GetMapping("/get")
    public Person getUserPersonalData(@RequestBody Integer id) {
        Optional<Person> personOptional = userService.getUserPersonalData(id);
        if (personOptional.isEmpty()) {
            return null; // TODO return error message
        }
        return personOptional.get();
    }

    @PostMapping("/change_status")
    public StatusChange changeUserStatus(@RequestBody Integer id, String newStatusValue) {
        StatusChange statusChange;
        try {
            statusChange = userService.changeUserStatus(id, newStatusValue);
        } catch (NoSuchElementException ex) {
            return null; // TODO return error message
        }
        return statusChange;
    }
}
