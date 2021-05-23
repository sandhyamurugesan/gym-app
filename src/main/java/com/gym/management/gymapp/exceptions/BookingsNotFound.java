package com.gym.management.gymapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookingsNotFound extends RuntimeException {
    public BookingsNotFound(String message) {
        super(message);
    }

}
