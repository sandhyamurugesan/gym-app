package com.gym.management.gymapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GymClassesNotPresent extends RuntimeException {
    public GymClassesNotPresent(String message) {
        super(message);
    }

}
