package com.hotel.hotelmanagement.dto;

public class UserRequestDto {
    private String username;
    private String email;
    private String phone;
    private String type;

    //constructor
//    public UserRequestDto(){};

    public UserRequestDto(String username, String email, String phone,String type){
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.type=type;
    }

    //Getter
    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public String getType() {
        return type;
    }

    //Setter
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
}

