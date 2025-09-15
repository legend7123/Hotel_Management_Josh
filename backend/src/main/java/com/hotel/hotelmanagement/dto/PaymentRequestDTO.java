package com.hotel.hotelmanagement.dto;

public class PaymentRequestDTO {
    private Long userId;
    private  Float amount;
    private Boolean status;

    //constructor
    public PaymentRequestDTO(Long userId, Float amount, Boolean status){
        this.userId= userId;
        this.amount=amount;
        this.status=status;
    }

    //getter
    public Long getUserId() {
        return userId;
    }

    public Float getAmount() {
        return amount;
    }

    public Boolean getStatus() {
        return status;
    }

    //setter
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
