package com.example.demo.controllers;

import com.example.demo.controllers.dto.AddUserDTO;
import com.example.demo.entities.Person;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.StringReader;
import java.io.StringWriter;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonFactory jsonFactory = new JsonFactory();

    @Test
    void addUser_goodUser_returnPerson() throws Exception {
        AddUserDTO userDTO = new AddUserDTO("a", "@", "1");
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
        objectMapper.writeValue(jsonGenerator, userDTO);
        jsonGenerator.close();
        String jsonRequest = stringWriter.toString();

        Person expectedPerson = new Person();
        expectedPerson.setId(100);
        Mockito.when(userService.addUser(any()))
                .thenReturn(expectedPerson);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        StringReader stringReader = new StringReader(response);
        Person person = objectMapper.readValue(
                jsonFactory.createParser(stringReader),
                Person.class
        );

        Assertions.assertEquals(expectedPerson.getId(), person.getId());
    }
}