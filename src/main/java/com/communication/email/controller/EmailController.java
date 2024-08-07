package com.communication.email.controller;

import com.communication.email.model.Email;
import com.communication.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/emails")
@Validated
public class EmailController {

    final EmailService emailService;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Async
    public void sendEmail(@RequestBody @Valid Email email) {
        emailService.sendEmail(email);
    }

}
