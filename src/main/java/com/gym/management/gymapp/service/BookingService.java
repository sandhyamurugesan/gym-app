package com.gym.management.gymapp.service;

import com.gym.management.gymapp.model.Booking;
import com.gym.management.gymapp.model.GymClass;
import com.gym.management.gymapp.repository.BoookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {


    @Autowired
    BoookingRepository bookingRepository;

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> findByGymClass(GymClass classId) {

        return bookingRepository.findByGymClass(classId);
    }
}
