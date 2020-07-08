package com.example.demo.services;

import com.example.demo.entities.Status;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.projections.ProfileOnly;
import com.example.demo.services.impl.StatisticServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class StatisticServiceTest {

    @Mock
    private StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
    @Mock
    private StatusChangeRepository statusChangeRepository = Mockito.mock(StatusChangeRepository.class);
    private StatisticService statisticService = new StatisticServiceImpl(statusRepository, statusChangeRepository);

    @BeforeTestClass
    public void initStatusRepository() {
        Status online = Mockito.mock(Status.class);
        Mockito.when(online.getValue()).thenReturn("Online");
        Mockito.when(statusRepository.findByValue("Online")).thenReturn(Optional.of(online));

        Status offline = Mockito.mock(Status.class);
        Mockito.when(online.getValue()).thenReturn("Offline");
        Mockito.when(statusRepository.findByValue("Offline")).thenReturn(Optional.of(offline));

        Mockito.when(statusRepository.findByValue(null)).thenThrow(IllegalArgumentException.class);
//        Mockito.when(statusRepository.findByValue(
//                Mockito.argThat(arg -> arg.equals("Online") || arg.equals("Offline"))))
//                .thenReturn(Optional.of(Mockito.mock(Status.class)));
        Mockito.when(statusRepository.findByValue(
                Mockito.argThat(arg -> !arg.equals("Online") && !arg.equals("Offline"))))
                .thenReturn(Optional.empty());
    }

    @BeforeTestClass
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
    public void get_differentStatusValue_returnFilteredByStatus() {
        Iterable<ProfileOnly> onlines = List.of();
        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(
                Mockito.argThat(arg -> arg.getValue().equals("Online"))))
                .thenReturn(onlines);

//        Iterable<ProfileOnly> offlines = List.of();
////                Mockito.mock(ProfileOnly.class),
////                Mockito.mock(ProfileOnly.class)
////        );
//        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(
//                Mockito.argThat(arg -> arg.getValue().equals("Offline"))))
//                .thenReturn(offlines);
//        Iterable<ProfileOnly> offlines = List.of();
//        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(
//                Mockito.argThat(arg -> arg.getValue().equals("Offline"))))
//                .thenReturn(offlines);

        Assertions.assertEquals(statisticService.get("Online", null), onlines);
        //Assertions.assertEquals(statisticService.get("Offline", null), offlines);
//        NoSuchElementException ex = Assertions.assertThrows(NoSuchElementException.class,
//                () -> statisticService.get("BadStatus", null));
//        Assertions.assertEquals(ex.getMessage(), "Impossible status");
    }

    @Test
    public void get_allParamsIsNotNull_returnFilteredByStatusAndTime() {
        // TODO
    }
}
