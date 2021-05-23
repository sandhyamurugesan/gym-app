package com.gym.management.gymapp.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
//import javax.validation.constraints.NotNull;

@Entity
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int class_id;

    String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date start_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date end_date;

    int capacity;
    long num_classes;

    public GymClass() {
    }

    public GymClass(int class_id, String name, Date start_date, Date end_date, int capacity, int num_classes) {
        this.class_id = class_id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.capacity = capacity;
        this.num_classes = num_classes;

    }

    public int getClass_id() {
        return class_id;
    }

    public String getName() {
        return name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getNum_classes() {
        return num_classes;
    }

    public void setNum_classes(long num_classes) {
        this.num_classes = num_classes;
    }

    @Override
    public String toString() {
        return "Gym Class: " +
                "name='" + name + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", capacity=" + capacity +
                '.';
    }
}
