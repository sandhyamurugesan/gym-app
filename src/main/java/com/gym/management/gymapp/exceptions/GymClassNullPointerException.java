package com.gym.management.gymapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GymClassNullPointerException extends RuntimeException {
    public GymClassNullPointerException(String message) {
        super(message);
    }
}
