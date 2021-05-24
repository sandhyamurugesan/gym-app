package com.gym.management.gymapp.controller;

import com.gym.management.gymapp.exceptions.GymClassNullPointerException;
import com.gym.management.gymapp.exceptions.GymClassServiceException;
import com.gym.management.gymapp.model.GymClass;
import com.gym.management.gymapp.service.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class GymClassController {
    @Autowired
    GymClassService gymClassService;

    @GetMapping
    public List<GymClass> getGymClasses() {
        List<GymClass> gymClasses;
        try {
            gymClasses = gymClassService.getAllClasses();
        } catch (GymClassServiceException e) {
            throw new GymClassServiceException("Gym Class Service Exception: " + e);
        }
        return gymClasses;
    }

    @PostMapping
    @ResponseBody
    public GymClass createClasses(@RequestBody GymClass gymClass) {
        try {
            if (gymClass.getName() == null || gymClass.getStart_date() == null || gymClass.getEnd_date() == null)
                throw new GymClassNullPointerException("Json request has invalid class name or start/end dates. Check those fields.");

            gymClassService.setNumOfClasses(gymClass);
            gymClassService.save(gymClass);
        } catch (GymClassServiceException e) {
            throw new GymClassServiceException("Gym Class Service Exception: " + e);
        }
        return gymClass;

    }
}
