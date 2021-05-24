package com.gym.management.gymapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.management.gymapp.model.Booking;
import com.gym.management.gymapp.model.GymClass;
import com.gym.management.gymapp.service.BookingService;
import com.gym.management.gymapp.service.GymClassService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        value = "Booking Rest API test",
        classes = GymAppApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class BookingRestAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private GymClassService gymClassService;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    String jsonMimeType = "application/json";

    @Test
    public void givenGymClassBookingInfo_whenClassInfo_then200Success() throws Exception {
        //given
        GymClass gymClassYoga = new GymClass(1, "Yoga", formatter.parse("01-06-2021"), formatter.parse("10-06-2021"), 10, 10);
        Optional<GymClass> gymClassOptional = Optional.of(gymClassYoga);
        doReturn(gymClassOptional).when(gymClassService).getClassById(1);
        Booking booking = new Booking(1, "Sandhya", formatter.parse("02-06-2021"));
        doReturn(Lists.newArrayList(booking)).when(bookingService).findByGymClass(gymClassYoga);

        this.mockMvc.perform(get("/bookings/1").contentType(jsonMimeType)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sandhya")));
    }


    @Test
    public void givenBookingInfoGymClasses_whenCreateBooking_then200Success() throws Exception {

        //given
        givenGymClassDetails();

        Booking booking = new Booking(1, "Sandhya", formatter.parse("02-06-2021"));
        doNothing().when(bookingService).save(booking);
        //when
        this.mockMvc.perform(post("/bookings").content(asJsonString(booking)).contentType(jsonMimeType)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.customer_name", is("Sandhya")));

    }

    @Test
    public void givenBookingInfoGymClasses_whenCreateBooking_then404NotFound() throws Exception {

        //given
        givenGymClassDetails();

        Booking booking = new Booking(1, "Sandhya", formatter.parse("12-06-2021")); //date out of range of the classes present
        doNothing().when(bookingService).save(booking);
        //when
        this.mockMvc.perform(post("/bookings").content(asJsonString(booking)).contentType(jsonMimeType)).andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    public void givenBookingInfoGymClasses_whenCreateBooking_then400BadRequest() throws Exception {

        //given
        givenGymClassDetails();

        Booking booking = new Booking(); //empty Booking object
        doNothing().when(bookingService).save(booking);
        //when
        this.mockMvc.perform(post("/bookings").content(asJsonString(booking)).contentType(jsonMimeType)).andDo(print()).andExpect(status().isBadRequest());

    }

    private void givenGymClassDetails() throws ParseException {
        GymClass gymClassYoga = new GymClass(1, "Yoga", formatter.parse("01-06-2021"), formatter.parse("10-06-2021"), 10, 10);
        doReturn(Lists.newArrayList(gymClassYoga)).when(gymClassService).getAllClasses();
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
