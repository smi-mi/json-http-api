package com.example.demo.controllers;

import com.example.demo.repositories.projections.ProfileOnly;
import com.example.demo.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public Iterable<ProfileOnly> getStatistic(
            @RequestParam(required = false) Long timestamp,
            @RequestParam(required = false) String status
    ) {
        return statisticService.get(status, timestamp);
    }
}
