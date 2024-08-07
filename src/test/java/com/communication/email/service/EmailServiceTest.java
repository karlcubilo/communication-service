package com.communication.email.service;

import com.communication.base.BaseUnitTest;
import com.communication.email.EmailException;
import com.communication.email.builder.MimeMessageBuilder;
import com.communication.email.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailServiceTest extends BaseUnitTest {

    @InjectMocks
    EmailServiceImpl emailService;

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    MimeMessageBuilder mimeMessageBuilder;

    @Mock
    MimeMessage mimeMessage;

    @Captor
    ArgumentCaptor<MimeMessage> mimeMessageArgumentCaptor;

    @Captor
    ArgumentCaptor<Email> emailArgumentCaptor;

    @Test
    public void sendEmail_whenSuccess_verify() throws MessagingException {

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(mimeMessageBuilder.build(mimeMessageArgumentCaptor.capture(), emailArgumentCaptor.capture())).thenReturn(mimeMessage);
        doNothing().when(javaMailSender).send(mimeMessageArgumentCaptor.capture());

        Email email = new Email();
        email.setTo(List.of("karl"));
        email.setFrom("from");
        email.setText("text");
        email.setIsHtml(true);

        emailService.sendEmail(email);

        verify(javaMailSender, atLeastOnce()).send(any(MimeMessage.class));
        assertNotNull(mimeMessageArgumentCaptor.getValue());
        assertThat(emailArgumentCaptor.getValue().getFrom(), equalTo("from"));
        assertThat(emailArgumentCaptor.getValue().getText(), equalTo("text"));
        assertThat(emailArgumentCaptor.getValue().getIsHtml(), equalTo(true));
        assertThat(emailArgumentCaptor.getValue().getTo().get(0), equalTo("karl"));
    }

    @Test
    public void sendEmail_whenFailed_verify() throws MessagingException {

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(mimeMessageBuilder.build(mimeMessageArgumentCaptor.capture(), emailArgumentCaptor.capture())).thenReturn(mimeMessage);

        doThrow(new MailSendException("test")).when(javaMailSender).send(mimeMessageArgumentCaptor.capture());

        Email email = new Email();
        email.setTo(List.of("karl"));
        email.setFrom("from");
        email.setText("text");
        email.setIsHtml(true);

        EmailException emailException = assertThrows(EmailException.class, () ->{
            emailService.sendEmail(email);
        });

        assertNotNull(mimeMessageArgumentCaptor.getValue());
        assertThat(emailArgumentCaptor.getValue().getFrom(), equalTo("from"));
        assertThat(emailArgumentCaptor.getValue().getText(), equalTo("text"));
        assertThat(emailArgumentCaptor.getValue().getIsHtml(), equalTo(true));
        assertThat(emailArgumentCaptor.getValue().getTo().get(0), equalTo("karl"));
        assertNotNull(emailException);
        assertNotNull(emailException.getMessage());
    }
}
