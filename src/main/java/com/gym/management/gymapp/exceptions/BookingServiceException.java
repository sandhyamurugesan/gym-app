package com.gym.management.gymapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BookingServiceException extends RuntimeException {
    public BookingServiceException(String message) {
        super(message);
    }
}
