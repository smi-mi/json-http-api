package com.example.demo.controllers;

import com.example.demo.entities.StatusChange;
import com.example.demo.services.StatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatusChangeService statusChangeService;

    @GetMapping("")
    public Iterable<StatusChange> getStatistic() {
        throw new UnsupportedOperationException();
    }
}
