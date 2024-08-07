package com.communication.email.builder;

import com.communication.base.BaseUnitTest;
import com.communication.email.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MimeMessageBuilderTest extends BaseUnitTest {

    @InjectMocks
    MimeMessageBuilder mimeMessageBuilder;

    @Mock
    MimeMessage mimeMessage;

    @Test
    public void build_whenParametersProvided_returnMimeMessage() throws MessagingException {

        Email email = new Email();
        email.setFrom("test@gmail.com");
        email.setTo(List.of("test@gmail.com"));
        email.setSubject("subject");
        email.setText("Text");
        email.setIsHtml(true);

        MimeMessage returnMessage = mimeMessageBuilder.build(mimeMessage,email);

        assertNotNull(returnMessage);
    }

    @Test
    public void build_whenParametersProvidedAndOthersProvided_returnMimeMessage() throws MessagingException {

        Email email = new Email();
        email.setFrom("test@gmail.com");
        email.setTo(List.of("test@gmail.com"));
        email.setSubject("subject");
        email.setText("Text");
        email.setCc(List.of("cc"));
        email.setBcc(List.of("bcc"));
        email.setIsHtml(true);

        MimeMessage returnMessage = mimeMessageBuilder.build(mimeMessage,email);

        assertNotNull(returnMessage);
    }
}
