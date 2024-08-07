package com.communication.email.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Email {

    String from;
    List<String> to;
    List<String> cc;
    List<String> bcc;
    String subject;
    String text;
    Boolean isHtml;
}
