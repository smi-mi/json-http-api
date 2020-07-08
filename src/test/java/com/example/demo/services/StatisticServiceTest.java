package com.example.demo.services;

import com.example.demo.entities.Status;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@AllArgsConstructor
@SpringBootTest
public class StatisticServiceTest {

    @MockBean
    private final StatusRepository statusRepository;
    @MockBean
    private final StatusChangeRepository statusChangeRepository;
    @Autowired
    private final StatisticService statisticService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void initStatusRepository() {
//        Status online = Mockito.mock(Status.class);
//        Mockito.when(online.getValue()).thenReturn("Online");
//        Mockito.when(statusRepository.findByValue("Online")).thenReturn(Optional.of(online));
//
//        Status offline = Mockito.mock(Status.class);
//        Mockito.when(online.getValue()).thenReturn("Offline");
//        Mockito.when(statusRepository.findByValue("Offline")).thenReturn(Optional.of(offline));

        Mockito.when(statusRepository.findByValue(null)).thenThrow(IllegalArgumentException.class);
        Mockito.when(statusRepository.findByValue(
                Mockito.argThat(arg -> arg.equals("Online") || arg.equals("Offline"))))
                .thenReturn(Optional.of(Mockito.mock(Status.class)));
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
        // TODO
    }

    @Test
    public void get_statusValueIsNull_returnFilteredByTime() {
        // TODO
    }

    @Test
    public void get_timestampIsNull_returnFilteredByStatus() {
        // TODO
    }

    @Test
    public void get_allParamsIsNotNull_returnFilteredByStatusAndTime() {
        // TODO
    }
}
