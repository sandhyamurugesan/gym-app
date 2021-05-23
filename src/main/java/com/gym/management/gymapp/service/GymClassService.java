package com.gym.management.gymapp.service;

import com.gym.management.gymapp.model.GymClass;
import com.gym.management.gymapp.repository.GymClassRepository;
import com.gym.management.gymapp.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymClassService {
    @Autowired
    GymClassRepository gymClassRepository;

    public void save(GymClass gymClass) {
        gymClassRepository.save(gymClass);
    }

    public List<GymClass> getAllClasses() {
        return (List<GymClass>) gymClassRepository.findAll();
    }

    public Optional<GymClass> getClassById(int classId) {
        return gymClassRepository.findById(classId);
    }

    public GymClass setNumOfClasses(GymClass gymClass) {
        long days_difference = DateUtils.difference(gymClass.getStart_date(), gymClass.getEnd_date());
        gymClass.setNum_classes(days_difference);
        return gymClass;
    }
}
