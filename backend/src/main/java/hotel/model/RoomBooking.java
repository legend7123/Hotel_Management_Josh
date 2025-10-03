package model;

import java.time.LocalDate;

public class RoomBooking {
    private Long id;
    private User user;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double totalPrice;

    public RoomBooking() {}

    public RoomBooking(Long id, User user, Room room, LocalDate checkIn, LocalDate checkOut, Double totalPrice) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public RoomBooking(User user, Room room, LocalDate checkIn, LocalDate checkOut, Double totalPrice) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }

    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}
