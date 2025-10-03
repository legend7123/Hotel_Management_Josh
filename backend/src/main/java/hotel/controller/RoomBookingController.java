package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dto.ResponseDTO;
import model.RoomBooking;
import model.Room;
import model.User;
import service.RoomBookingService;
import util.HelperUtils;
import util.ResponseUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class RoomBookingController {
    private final RoomBookingService bookingService;

    public RoomBookingController(RoomBookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void registerRoutes(HttpServer server) {
        server.createContext("/bookings", this::handleBookingPath);
        server.createContext("/bookings/", this::handleBookingIdPath);
    }

    private void handleBookingPath(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod().toUpperCase()) {
            case "GET": getAll(exchange); break;
            case "POST": create(exchange); break;
            case "PUT": update(exchange); break;
            default: ResponseUtils.sendResponse(exchange, new ResponseDTO(405, "Method Not Allowed"));
        }
    }

    private void handleBookingIdPath(HttpExchange exchange) throws IOException {
        String[] parts = exchange.getRequestURI().getPath().split("/");
        if (parts.length == 3) {
            Long id = Long.parseLong(parts[2]);
            switch (exchange.getRequestMethod().toUpperCase()) {
                case "GET": getOne(exchange, id); break;
                case "DELETE": delete(exchange, id); break;
                default: ResponseUtils.sendResponse(exchange, new ResponseDTO(405, "Method Not Allowed"));
            }
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        List<RoomBooking> bookings = bookingService.getAllBookings();
        ResponseUtils.sendResponse(exchange, new ResponseDTO(200, bookings));
    }

    private void getOne(HttpExchange exchange, Long id) throws IOException {
        try {
            RoomBooking booking = bookingService.getBooking(id);
            ResponseUtils.sendResponse(exchange, new ResponseDTO(200, booking));
        } catch (RuntimeException e) {
            ResponseUtils.sendResponse(exchange, new ResponseDTO(404, e.getMessage()));
        }
    }

    private void create(HttpExchange exchange) throws IOException {
        RoomBooking booking = parseRequest(exchange);
        RoomBooking created = bookingService.createBooking(booking);
        ResponseUtils.sendResponse(exchange, new ResponseDTO(201, created));
    }

    private void update(HttpExchange exchange) throws IOException {
        RoomBooking booking = parseRequest(exchange);
        RoomBooking updated = bookingService.updateBooking(booking);
        ResponseUtils.sendResponse(exchange, new ResponseDTO(200, updated));
    }

    private void delete(HttpExchange exchange, Long id) throws IOException {
        bookingService.deleteBooking(id);
        ResponseUtils.sendResponse(exchange, new ResponseDTO(204, -1));
    }

    private RoomBooking parseRequest(HttpExchange exchange) throws IOException {
        String body = readBody(exchange);
        Long id = parseLong(HelperUtils.extractJsonValue(body, "id"));
        Long userId = parseLong(HelperUtils.extractJsonValue(body, "userId"));
        Long roomId = parseLong(HelperUtils.extractJsonValue(body, "roomId"));
        LocalDate checkIn = LocalDate.parse(HelperUtils.extractJsonValue(body, "checkIn"));
        LocalDate checkOut = LocalDate.parse(HelperUtils.extractJsonValue(body, "checkOut"));
        Double totalPrice = Double.parseDouble(HelperUtils.extractJsonValue(body, "totalPrice"));

        RoomBooking booking = new RoomBooking();
        booking.setId(id);
        User user = new User(); user.setId(userId); booking.setUser(user);
        Room room = new Room(); room.setId(roomId); booking.setRoom(room);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setTotalPrice(totalPrice);
        return booking;
    }

    private String readBody(HttpExchange exchange) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
        StringBuilder buf = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) buf.append(line);
        return buf.toString();
    }

    private Long parseLong(String value) {
        return (value == null || value.isEmpty()) ? null : Long.parseLong(value);
    }
}
