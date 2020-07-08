package com.example.demo.services;

import com.example.demo.entities.Profile;
import com.example.demo.entities.Status;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.projections.ProfileOnly;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

    @Test
    public void get_allParamsIsNull_returnAllStatusChanges() {
        Iterable<ProfileOnly> statusChangeResponse = List.of(
                new ProfileOnly(new Profile()),
                new ProfileOnly(new Profile()),
                new ProfileOnly(new Profile())
        );
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThan(any()))
                .thenReturn(statusChangeResponse);

        Iterable<ProfileOnly> response = statisticService.get(null, null);

        Assertions.assertEquals(statusChangeResponse, response);
    }

    @Test
    public void get_statusValueIsNull_returnFilteredByTime() {
        Iterable<ProfileOnly> statusChangeResponse = List.of(
                new ProfileOnly(new Profile()),
                new ProfileOnly(new Profile())
        );
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThan(any()))
                .thenReturn(statusChangeResponse);

        final Long timestamp = 1000L;
        Iterable<ProfileOnly> response = statisticService.get(null, timestamp);

        Assertions.assertEquals(statusChangeResponse, response);
    }

    @Test
    public void get_statusValueIsOnline_returnFilteredByStatus() {
        Iterable<ProfileOnly> onlines = List.of(
                new ProfileOnly(new Profile()),
                new ProfileOnly(new Profile())
        );
        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(
                Mockito.argThat(arg -> arg.getValue().equals("Online"))))
                .thenReturn(onlines);

        Assertions.assertEquals(onlines, statisticService.get("Online", null));
    }

    @Test
    public void get_statusValueIsOffline_returnFilteredByStatus() {
        Iterable<ProfileOnly> offlines = List.of(new ProfileOnly(new Profile()));
        Mockito.when(statusChangeRepository.findDistinctByProfileStatusLike(
                Mockito.argThat(arg -> arg.getValue().equals("Offline"))))
                .thenReturn(offlines);

        Assertions.assertEquals(offlines, statisticService.get("Offline", null));
    }

    @Test
    public void get_badStatusValue_throwNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> statisticService.get("BadStatus", null)
        );
    }

    @Test
    public void get_allParamsIsNotNull_returnFilteredByStatusAndTime() {
        Iterable<ProfileOnly> expectedResponse = List.of(
                new ProfileOnly(new Profile()),
                new ProfileOnly(new Profile()),
                new ProfileOnly(new Profile())
        );
        Mockito.when(statusChangeRepository.findDistinctByTimestampGreaterThanAndProfileStatusLike(
                Mockito.anyLong(), any()))
                .thenReturn(expectedResponse);

        Assertions.assertEquals(expectedResponse,
                statisticService.get("Online", 100L)
        );
    }
}
