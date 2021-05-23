package com.gym.management.gymapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.management.gymapp.model.GymClass;
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

import java.sql.Date;

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
        value = "Mock",
        classes = GymAppApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class GymRestAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GymClassService gymClassService;

    @Test
    public void givenGymClasses_whenClassInfo_then200Success() throws Exception {
        //given
        GymClass gymClassYoga = new GymClass(1, "Yoga", Date.valueOf("2021-06-01"), Date.valueOf("2021-06-10"), 10, 10);
        GymClass gymClassPilates = new GymClass(2, "Pilates", Date.valueOf("2021-07-01"), Date.valueOf("2021-07-10"), 20, 10);
        doReturn(Lists.newArrayList(gymClassYoga, gymClassPilates)).when(gymClassService).getAllClasses();


        this.mockMvc.perform(get("/classes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Yoga")));
    }

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws Exception {

        //given
        String jsonMimeType = "application/json";

        this.mockMvc.perform(get("/classes")).andDo(print()).andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString(jsonMimeType)));

    }

    @Test
    public void givenGymClasses_whenCreateClass_then200Success() throws Exception {
        String jsonMimeType = "application/json";
        //given
        GymClass gymClassYoga = new GymClass(1, "Yoga", Date.valueOf("2021-06-01"), Date.valueOf("2021-06-10"), 10, 10);
        doNothing().when(gymClassService).save(gymClassYoga);

        //when
        this.mockMvc.perform(post("/classes").content(asJsonString(gymClassYoga)).contentType(jsonMimeType)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.class_id", is(1)));

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
