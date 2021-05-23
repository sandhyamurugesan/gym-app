package com.gym.management.gymapp.controller;

import com.gym.management.gymapp.exceptions.BookingServiceException;
import com.gym.management.gymapp.exceptions.BookingsNotFound;
import com.gym.management.gymapp.exceptions.GymClassesNotPresent;
import com.gym.management.gymapp.model.Booking;
import com.gym.management.gymapp.model.GymClass;
import com.gym.management.gymapp.service.BookingService;
import com.gym.management.gymapp.service.GymClassService;
import com.gym.management.gymapp.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    GymClassService gymClassService;

    @GetMapping("/{classId}")
    @ResponseBody
    public List<Booking> getBookingByClass(@PathVariable int classId) {
        List<Booking> bookings;
        try {
            GymClass gymClass = gymClassService.getClassById(classId).orElseThrow(() -> new GymClassesNotPresent("No classes present for this id"));
            bookings = bookingService.findByGymClass(gymClass);
            if (bookings.isEmpty())
                throw new BookingsNotFound("No Bookings present for this class");
        } catch (BookingServiceException e) {
            throw new BookingServiceException("Service Exception: " + e);
        }
        return bookings;
    }


    @PostMapping("")
    @ResponseBody
    public Booking createBookings(@RequestBody Booking booking) {
        try {
            GymClass gymClass = gymClassService.getAllClasses().stream().filter(gc -> {
                return DateUtils.between(booking.getClass_date(), gc.getStart_date(), gc.getEnd_date());
            }).findFirst().orElseThrow(() -> new GymClassesNotPresent("No classes present for the given booking date"));

            booking.setGymClass(gymClass);
            bookingService.save(booking);
        } catch (BookingServiceException e) {
            throw new BookingServiceException("Service Exception: " + e);
        }
        return booking;

    }

}
