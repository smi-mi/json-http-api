package com.example.demo.controllers;

import com.example.demo.controllers.dto.ChangeStatusDTO;
import com.example.demo.controllers.dto.GetUserDTO;
import com.example.demo.entities.Person;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/add")
    public Person addUser(@RequestBody Person person) {
        return userService.addUser(person);
    }

    @GetMapping("/get")
    public Person getUserPersonalData(@RequestBody GetUserDTO getUserDTO) {
        Integer id = getUserDTO.getId();
        Optional<Person> personOptional = userService.getUserPersonalData(id);
        if (personOptional.isEmpty()) {
            return null; // TODO return error message
        }
        return personOptional.get();
    }

    @PostMapping("/change_status")
    public StatusChange changeUserStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        Integer id = changeStatusDTO.getId();
        String newStatusValue = changeStatusDTO.getNewStatusValue();
        StatusChange statusChange;
        try {
            statusChange = userService.changeUserStatus(id, newStatusValue);
        } catch (NoSuchElementException ex) {
            return null; // TODO return error message
        }
        return statusChange;
    }
}
