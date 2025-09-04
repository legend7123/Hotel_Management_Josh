package com.hotel.hotelmanagement.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message){
        super(message);
    }
}
