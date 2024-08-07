package com.communication.email.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Email {

    @NotBlank
    String from;
    @NotNull
    List<String> to;
    List<String> cc;
    List<String> bcc;
    @NotNull
    String subject;
    @NotBlank
    String text;
    Boolean isHtml;
}
