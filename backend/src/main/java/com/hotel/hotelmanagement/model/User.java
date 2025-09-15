package com.hotel.hotelmanagement.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String phone;
    private String type;
    private Integer loyalty=0;

    //one user -> many room bookings
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
//    private Set<RoomBooking> roomBookings = new HashSet<>();
//
//    //one user -> many amenity bookings
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
//    private Set<AmenityBooking> amenityBookings = new HashSet<>();
//
//    //one user -> many payments
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
//    private Set<Payment> payments = new HashSet<>();

    //Constructors
    public User(){};

    public User(String username,String email,String phone,String type){
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.type=type;
    };

    //Getters
    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return  email;
    }

    public String getPhone(){
        return phone;
    }

    public String getType() {
        return type;
    }

    public Integer getLoyalty(){
        return loyalty;
    }

    //Setters
    public void setUsername(String username){
        this.username=username;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLoyalty(Integer loyalty){
        this.loyalty=loyalty;
    }
}
