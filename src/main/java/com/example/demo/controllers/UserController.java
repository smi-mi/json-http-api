package com.example.demo.controllers;

import com.example.demo.entities.Person;
import com.example.demo.entities.Profile;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.PersonService;
import com.example.demo.services.ProfileService;
import com.example.demo.services.StatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StatusChangeService statusChangeService;

    @PostMapping("/add")
    public Person addNewPerson() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/get")
    public Profile getProfile() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/change_status")
    public StatusChange changeStatus() {
        throw new UnsupportedOperationException();
    }
}
