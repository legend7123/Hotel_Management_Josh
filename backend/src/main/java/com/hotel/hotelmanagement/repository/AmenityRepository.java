package com.hotel.hotelmanagement.repository;

import com.hotel.hotelmanagement.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity,Long> {

}
