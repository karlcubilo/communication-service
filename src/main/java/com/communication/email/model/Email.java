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

    @NotBlank(message = "From is required")
    String from;
    @NotNull(message = "To is required")
    List<String> to;
    List<String> cc;
    List<String> bcc;
    @NotNull(message = "Subject is required")
    String subject;
    @NotBlank(message = "Text is required")
    String text;
    Boolean isHtml;
}
