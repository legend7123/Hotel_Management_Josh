package com.hotel.hotelmanagement.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "amenity_booking")
public class AmenityBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private Long amenity_id;
    private Timestamp start_time;
    private Timestamp end_time;

//    @ManyToOne
//    @JoinColumn(name = "amenity_id", nullable = false)
//    private Amenity amenity;
//
//    private Timestamp startTime;
//    private Timestamp endTime;

    // Constructors
    public AmenityBooking() {}

    public AmenityBooking(Long user_id, Long amenity_id, Timestamp start_time, Timestamp end_time) {
        this.user_id=user_id;
        this.amenity_id = amenity_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getAmenity_id(){
        return amenity_id;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    //setter

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setAmenity_id(Long amenity_id) {
        this.amenity_id = amenity_id;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }
}