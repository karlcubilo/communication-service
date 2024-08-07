package com.communication.email.controller;

import com.communication.email.model.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mock")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailControllerFeatureTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void sendEmail_whenValid_returnAccepted() throws Exception {

        Email email = new Email();
        email.setFrom("123");
        email.setTo(List.of("123"));
        email.setText("!23");
        email.setSubject("123");

        mockMvc.perform(MockMvcRequestBuilders.post("/emails")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(email))
                ).andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();
    }
}
