package com.communication.email.builder;

import com.communication.email.model.Email;
import com.communication.util.ArrayUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MimeMessageBuilder {

    public MimeMessage build(MimeMessage mimeMessage, Email email) throws MessagingException {

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setTo(ArrayUtils.convertListToArray(email.getTo()));
        if(Objects.nonNull(email.getCc())) {
            mimeMessageHelper.setCc(ArrayUtils.convertListToArray(email.getCc()));
        }
        if(Objects.nonNull(email.getBcc())) {
            mimeMessageHelper.setBcc(ArrayUtils.convertListToArray(email.getBcc()));
        }
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(email.getText(), email.getIsHtml());

        return mimeMessage;
    }
}
