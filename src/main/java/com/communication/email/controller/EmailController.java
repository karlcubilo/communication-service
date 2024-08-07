package com.communication.email.controller;

import com.communication.email.model.Email;
import com.communication.email.service.EmailService;
import com.communication.email.service.EmailServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/emails")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmailController {

    final EmailService emailService;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendEmail(Email email) {
        emailService.sendEmail(email);
    }

}
