package com.communication.email;

import lombok.Getter;

@Getter
public class EmailException extends RuntimeException {

    String message;

    public EmailException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }
}
