package com.hotel.hotelmanagement.exception;

public class UserExists extends RuntimeException {
    public UserExists(String message) {
        super(message);
    }
}
