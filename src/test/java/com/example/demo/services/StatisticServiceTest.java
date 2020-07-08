package com.example.demo.services;

import com.example.demo.entities.Status;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.projections.ProfileOnly;
import com.example.demo.services.impl.StatisticServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticServiceTest {

    @MockBean
    private StatusRepository statusRepository;
    @MockBean
    private StatusChangeRepository statusChangeRepository;
    @Autowired
    private StatisticService statisticService;

    @BeforeEach
    public void initStatusRepository() {
        Status online = new Status("Online");
        Mockito.when(statusRepository.findByValue("Online"))
                .thenReturn(Optional.of(online));

        Status offline = new Status("Offline");
        Mockito.when(statusRepository.findByValue("Offline"))
                .thenReturn(Optional.of(offline));

        Mockito.when(statusRepository.findByValue(
                Mockito.argThat(arg -> !arg.equals("Online") && !arg.equals("Offline"))))
                .thenReturn(Optional.empty());
    }

    @BeforeEach
    public void initStatusChangeRepository() {
        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(null))
                .thenThrow(IllegalArgumentException.class);
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThan(null))
                .thenThrow(IllegalArgumentException.class);
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThanAndProfileStatusLike(
                null, null))
                .thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void get_allParamsIsNull_returnAllStatusChanges() {
        Iterable<ProfileOnly> statusChangeResponse = List.of();
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThan(any()))
                .thenReturn(statusChangeResponse);
        Iterable<ProfileOnly> response = statisticService.get(null, null);
        Assertions.assertEquals(response, statusChangeResponse);
    }

    @Test
    public void get_statusValueIsNull_returnFilteredByTime() {
        Iterable<ProfileOnly> statusChangeResponse = List.of();
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThan(any()))
                .thenReturn(statusChangeResponse);
        Iterable<ProfileOnly> response = statisticService.get(null, 1000L);
        Assertions.assertEquals(response, statusChangeResponse);
    }

    @Test
    public void get_statusValueIsOnline_returnFilteredByStatus() {
        Iterable<ProfileOnly> onlines = List.of();
        Status online = new Status("Online");
        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(online))
                .thenReturn(onlines);
        Assertions.assertEquals(statisticService.get("Online", null), onlines);
    }

    @Test
    public void get_statusValueIsOffline_returnFilteredByStatus() {
        Iterable<ProfileOnly> offlines = List.of();
        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(
                Mockito.argThat(arg -> arg.getValue().equals("Offline"))))
                .thenReturn(offlines);
        Assertions.assertEquals(statisticService.get("Offline", null), offlines);
    }

    @Test
    public void get_badStatusValue_throwNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> statisticService.get("BadStatus", null));
    }

    @Test
    public void get_allParamsIsNotNull_returnFilteredByStatusAndTime() {
        // TODO
    }
}
