package com.example.demo.controllers;

import com.example.demo.controllers.dto.GetStatisticDTO;
import com.example.demo.entities.Profile;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.projections.StatusChangeProjectProfile;
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
    public Iterable<StatusChangeProjectProfile> getStatistic(@RequestBody GetStatisticDTO getStatisticDTO) {
        Iterable<StatusChangeProjectProfile> response; // TODO return only profiles
        try {
            response = statisticService.get(
                    getStatisticDTO.getStatusValue(),
                    getStatisticDTO.getTimestamp()
            );
        } catch (Exception ex) {
            return null; // TODO return error message
        }
        return response;
    }
}
