package com.example.demo.controllers;

import com.example.demo.controllers.dto.AddUserDTO;
import com.example.demo.controllers.dto.ChangeStatusDTO;
import com.example.demo.entities.Person;
import com.example.demo.entities.StatusChange;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public Person addUser(@RequestBody AddUserDTO addUserDTO) {
        return userService.addUser(
                new Person(addUserDTO.getName(), addUserDTO.getEmail(), addUserDTO.getPhone())
        );
    }

    @GetMapping("/get")
    public Person getUserPersonalData(@RequestParam Integer id) {
        return userService.getUserPersonalData(id);
    }

    @PostMapping("/change_status")
    public StatusChange changeUserStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        Integer id = changeStatusDTO.getId();
        String newStatusValue = changeStatusDTO.getNewStatusValue();
        return userService.changeUserStatus(id, newStatusValue);
    }
}
