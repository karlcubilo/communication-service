package com.communication.email.service;

import com.communication.email.EmailException;
import com.communication.email.builder.MimeMessageBuilder;
import com.communication.email.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Profile("!mock")
@Slf4j
public class EmailServiceImpl implements EmailService{

    final JavaMailSender javaMailSender;
    final MimeMessageBuilder mimeMessageBuilder;

    public void sendEmail(Email email)  {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            javaMailSender.send(mimeMessageBuilder.build(mimeMessage,email));
        } catch(Exception e) {
            log.error("Cannot send email for request {}", email.toString(), e);
            throw new EmailException(e.getMessage(), e);
        }

    }
}
