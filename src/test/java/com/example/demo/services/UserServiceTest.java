package com.example.demo.services;

import com.example.demo.entities.Person;
import com.example.demo.entities.Profile;
import com.example.demo.entities.Status;
import com.example.demo.entities.StatusChange;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.ProfileRepository;
import com.example.demo.repositories.StatusChangeRepository;
import com.example.demo.repositories.StatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

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
    public void addUser_personIsNull_throwNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> userService.addUser(null)
        );
    }

    @Test
    public void addUser_personWithOnlyName_successfullySaved() {
        Person person = new Person();
        person.setName("Masha");

        userService.addUser(person);

        Mockito.verify(personRepository).save(person);
        Mockito.verify(profileRepository).save(any());
    }

    @Test
    public void addUser_personWithOnlyEmail_successfullySaved() {
        Person person = new Person();
        person.setEmail("m@m.ru");

        userService.addUser(person);

        Mockito.verify(personRepository).save(person);
        Mockito.verify(profileRepository).save(any());
    }

    @Test
    public void addUser_personWithOnlyPhone_successfullySaved() {
        Person person = new Person();
        person.setPhone("123");

        userService.addUser(person);

        Mockito.verify(personRepository).save(person);
        Mockito.verify(profileRepository).save(any());
    }

    @Test
    public void addUser_goodPerson_successfullySaved() {
        Person person = new Person("Masha", "m@m.ru", "123");

        userService.addUser(person);

        Mockito.verify(personRepository).save(person);
        Mockito.verify(profileRepository).save(any());
    }

    // getUserPersonalData() tests
    @Test
    public void getUserPersonalData_idIsNull_throwNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> userService.getUserPersonalData(null)
        );
    }

    @Test
    public void getUserPersonalData_noSuchId_throwNoSuchElementException() {
        Integer id = 100;
        Mockito.when(profileRepository.findByPersonId(Mockito.eq(id)))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class,
                () -> userService.getUserPersonalData(id)
        );
    }

    @Test
    public void getUserPersonalData_goodId_returnPerson() {
        Integer id = 7;
        Person expectedPerson = new Person("Mary", "mary@mail.ru", "12345");
        Mockito.when(personRepository.findById(Mockito.eq(id)))
                .thenReturn(Optional.of(expectedPerson));

        Assertions.assertEquals(expectedPerson, userService.getUserPersonalData(id));
    }

    // changeUserStatus() tests
    @Test
    public void changeUserStatus_allParamsIsNull_throwNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> userService.changeUserStatus(null, null)
        );
    }

    @Test
    public void changeUserStatus_idIsNull_throwNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> userService.changeUserStatus(null, "Online")
        );
    }

    @Test
    public void changeUserStatus_newStatusValueIsNull_throwNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> userService.changeUserStatus(10, null)
        );
    }

    @Test
    public void changeUserStatus_noSuchId_throwNoSuchElementException() {
        Mockito.when(profileRepository.findByPersonId(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class,
                () -> userService.changeUserStatus(10, "Online")
        );
    }

    @Test
    public void changeUserStatus_impossibleStatus_throwNoSuchElementException() {
        Mockito.when(profileRepository.findByPersonId(any()))
                .thenReturn(Optional.of(new Profile()));

        Assertions.assertThrows(NoSuchElementException.class,
                () -> userService.changeUserStatus(10, "BadStatus")
        );
    }

    @Test
    public void changeUserStatus_goodParams_returnStatusChangeInfo() {
        Profile expectedProfile = new Profile();
        Mockito.when(profileRepository.findByPersonId(any()))
                .thenReturn(Optional.of(expectedProfile));

        StatusChange statusChange = userService.changeUserStatus(10, "Offline");

        Assertions.assertEquals(expectedProfile, statusChange.getProfile());
        Mockito.verify(statusChangeRepository, Mockito.times(1))
                .save(statusChange);
        Mockito.verify(profileRepository, Mockito.times(1))
                .save(expectedProfile);
    }
}
