package com.gym.management.gymapp.repository;

import com.gym.management.gymapp.model.Booking;
import com.gym.management.gymapp.model.GymClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BoookingRepository extends CrudRepository<Booking, Integer> {

    public List<Booking> findByGymClass(GymClass classId);
}
