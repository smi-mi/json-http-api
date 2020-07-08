package com.example.demo.controllers;

import com.example.demo.controllers.dto.GetStatisticDTO;
import com.example.demo.repositories.projections.ProfileOnly;
import com.example.demo.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("")
    public Iterable<ProfileOnly> getStatistic(
            @RequestBody GetStatisticDTO getStatisticDTO
    ) {
        return statisticService.get(
                getStatisticDTO.getStatusValue(),
                getStatisticDTO.getTimestamp()
        );
    }
}
