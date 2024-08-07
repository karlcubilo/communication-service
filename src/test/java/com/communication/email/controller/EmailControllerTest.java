package com.communication.email.controller;

import com.communication.base.BaseUnitTest;
import com.communication.email.model.Email;
import com.communication.email.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailControllerTest extends BaseUnitTest {

    @InjectMocks
    EmailController emailController;

    @Mock
    EmailService emailService;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<Email> emailArgumentCaptor;


    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(emailController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void sendEmail_whenFromIsMissing_returnBadRequest() throws Exception {

        Email email = new Email();
        email.setTo(List.of("asd"));
        email.setText("!23");
        email.setSubject("subject");

        mockMvc.perform(MockMvcRequestBuilders.post("/emails")
                .contentType("application/json")
                .content(objectMapper.writeValueAsBytes(email))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    public void sendEmail_whenTextIsMissing_returnBadRequest() throws Exception {

        Email email = new Email();
        email.setFrom("123");
        email.setTo(List.of("asd"));
        email.setSubject("subject");

        mockMvc.perform(MockMvcRequestBuilders.post("/emails")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(email))
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    public void sendEmail_whenToIsMissing_returnBadRequest() throws Exception {

        Email email = new Email();
        email.setFrom("123");
        email.setText("!23");
        email.setSubject("subject");

        mockMvc.perform(MockMvcRequestBuilders.post("/emails")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(email))
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    public void sendEmail_whenSubjectIsMissing_returnBadRequest() throws Exception {

        Email email = new Email();
        email.setFrom("123");
        email.setTo(List.of("123"));
        email.setText("!23");

        mockMvc.perform(MockMvcRequestBuilders.post("/emails")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(email))
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
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
