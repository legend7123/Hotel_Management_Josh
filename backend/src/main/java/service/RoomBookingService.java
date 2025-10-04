package service;

import dao.RoomBookingDAO;
import model.RoomBooking;

import java.util.List;

public class RoomBookingService {
    private final RoomBookingDAO bookingDao;

    public RoomBookingService(RoomBookingDAO bookingDao) {
        this.bookingDao = bookingDao;
    }

    public RoomBooking createBooking(RoomBooking booking) {
        return bookingDao.bookRoom(booking);
    }

    public List<RoomBooking> getAllBookings() {
        return bookingDao.findAll();
    }

    public RoomBooking getBooking(Long id) {
        RoomBooking booking = bookingDao.findById(id);
        if (booking == null) throw new RuntimeException("Booking not found");
        return booking;
    }

    public RoomBooking updateBooking(RoomBooking booking) {
        return bookingDao.update(booking);
    }

    public void deleteBooking(Long id) {
        bookingDao.delete(id);
    }
}
