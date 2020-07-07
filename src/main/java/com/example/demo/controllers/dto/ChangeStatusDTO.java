package com.example.demo.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeStatusDTO {
    private Integer id;
    private String newStatusValue;
}
