package com.hotel.hotelmanagement.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.hotel.hotelmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //findById
    //findAll
    //save
    //deleteById
    //existsById are present by default

}
