package com.example.demo.controllers;

import com.example.demo.entities.Person;
import com.example.demo.entities.Profile;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Person addUser() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/get")
    public Profile getUserPersonalData() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/change_status")
    public StatusChange changeUserStatus() {
        throw new UnsupportedOperationException();
    }
}
