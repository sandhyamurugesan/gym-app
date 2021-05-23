package com.gym.management.gymapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@ApiModel
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(position = 1, hidden = true, notes = "auto generated")
    int booking_id;

    String customer_name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date class_date;

    @ManyToOne
    @JsonIgnore
    GymClass gymClass;

    public Booking() {
    }

    public Booking(int booking_id, String customer_name, Date class_date) {
        this.booking_id = booking_id;
        this.customer_name = customer_name;
        this.class_date = class_date;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public Date getClass_date() {
        return class_date;
    }

    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }

    @Override
    public String toString() {
        return "Booking: " +
                "booking_id=" + booking_id +
                ", customer_name='" + customer_name + '\'' +
                // ", class_name='" + class_name + '\'' +
                ", class_date='" + class_date + '\'' +
                '.';
    }
}
