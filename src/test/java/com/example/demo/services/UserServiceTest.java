package com.example.demo.services;

import com.example.demo.entities.Status;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.ProfileRepository;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private PersonRepository personRepository;
    @MockBean
    private ProfileRepository profileRepository;
    @MockBean
    private StatusRepository statusRepository;
    @MockBean
    private StatusChangeRepository statusChangeRepository;
    @Autowired
    private UserService userService;

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

    // addUser() tests
    @Test
    public void addUser_personIsNull_() {
        // TODO
    }

    @Test
    public void addUser_personWithOnlyName_() {
        // TODO
    }

    @Test
    public void addUser_personWithOnlyEmail_() {
        // TODO
    }

    @Test
    public void addUser_personWithOnlyPhone_() {
        // TODO
    }

    @Test
    public void addUser_goodPerson_successfullySaved() {
        // TODO
    }

    // getUserPersonalData() tests
    @Test
    public void getUserPersonalData_idIsNull_() {
        // TODO
    }

    @Test
    public void getUserPersonalData_noSuchId_() {
        // TODO
    }

    @Test
    public void getUserPersonalData_goodId_returnedPerson() {
        // TODO
    }

    // changeUserStatus() tests
    @Test
    public void changeUserStatus_allParamsIsNull_() {
        // TODO
    }

    @Test
    public void changeUserStatus_idIsNull_() {
        // TODO
    }

    @Test
    public void changeUserStatus_newStatusValueIsNull_() {
        // TODO
    }

    @Test
    public void changeUserStatus_noSuchId_throwNoSuchElementException() {
        // TODO
    }

    @Test
    public void changeUserStatus_impossibleStatus_throwNoSuchElementException() {
        // TODO
    }

    @Test
    public void changeUserStatus_goodParams_returnStatusChangeInfo() {
        // TODO
    }
}
