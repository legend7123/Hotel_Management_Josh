package com.hotel.hotelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "amenity")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;

    //constructors
    public Amenity() {}

    public Amenity(Integer type) {
        this.type = type;
    }

    //getter
    public Long getId() {
        return id;
    }

    public Integer getType(){
        return type;
    }

    //setter
    public void setType(Integer type) {
        this.type = type;
    }

//    @Transient
//    public Integer getCapacity() {
//        return id % 100; // last two digits = capacity
//    }
//    @Transient
//    public Integer getTableNumber() {
//        return id / 100; // first two digits = table number
//    }
}