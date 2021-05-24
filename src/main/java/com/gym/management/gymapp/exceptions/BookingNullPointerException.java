package com.gym.management.gymapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookingNullPointerException extends RuntimeException {
    public BookingNullPointerException(String message) {
        super(message);
    }
}
