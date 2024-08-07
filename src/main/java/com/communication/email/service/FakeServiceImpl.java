package com.communication.email.service;

import com.communication.email.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock")
@Slf4j
public class FakeServiceImpl implements EmailService{
    @Override
    public void sendEmail(Email email) {
        log.debug("email sent");
    }
}
