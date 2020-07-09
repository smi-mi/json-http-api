package com.example.demo.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddUserDTO {
    private String name;
    private String email;
    private String phone;
}
