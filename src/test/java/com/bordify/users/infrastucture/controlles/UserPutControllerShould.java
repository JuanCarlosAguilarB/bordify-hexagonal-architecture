package com.bordify.users.infrastucture.controlles;


import com.bordify.users.infrastructure.controllers.RequestUserBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest()
@AutoConfigureMockMvc
public class UserPutControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateUser() throws Exception {

        RequestUserBody requestUserBody = RequestUserBody.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("testpassword")
                .firstName("Test")
                .lastName("User")
                .phoneNumber("1234567890")
                .build();


        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUserBody))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
        )
        .andExpect(status().isCreated()) // we only will test your status code
        .andReturn();

    }

    @Test
    public void shouldNotCreateUserByDuplicatedEmail() throws Exception {

        RequestUserBody requestUserBody = RequestUserBody.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("testpassword")
                .firstName("Test")
                .lastName("User")
                .phoneNumber("1234567890")
                .build();


        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUserBody))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
        )
                .andExpect(status().isCreated()) // we only will test your status code
                .andReturn();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUserBody))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
        )
                .andExpect(status().isConflict())

                .andExpect(jsonPath("$.message",
                        CoreMatchers.containsString(requestUserBody.getEmail())
                ))
                .andReturn();

    }
}
