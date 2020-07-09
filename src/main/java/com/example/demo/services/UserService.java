package com.example.demo.services;

import com.example.demo.entities.Person;
import com.example.demo.entities.StatusChange;
import lombok.NonNull;

public interface UserService {

    Person addUser(@NonNull Person person);

    Person getUserPersonalData(@NonNull Integer id);

    StatusChange changeUserStatus(@NonNull Integer id, @NonNull String newStatusValue);
}
