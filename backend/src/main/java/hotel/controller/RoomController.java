package controller;

import service.RoomService;
import model.Room;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dto.ResponseDTO;
import util.ResponseUtils;
import util.HelperUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void registerRoutes(HttpServer server) {
        server.createContext("/rooms", this::handleRoomPath);
        server.createContext("/rooms/", this::handleRoomIdPath);
    }

    private void handleRoomPath(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod().toUpperCase()) {
            case "GET": getAll(exchange); break;
            case "POST": create(exchange); break;
            case "PUT": update(exchange); break;
            default: ResponseUtils.sendResponse(exchange, new ResponseDTO(405, "Method Not Allowed"));
        }
    }

    private void handleRoomIdPath(HttpExchange exchange) throws IOException {
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
        List<Room> rooms = roomService.getAllRooms();
        ResponseUtils.sendResponse(exchange, new ResponseDTO(200, rooms));
    }

    private void getOne(HttpExchange exchange, Long id) throws IOException {
        try {
            Room room = roomService.getRoom(id);
            ResponseUtils.sendResponse(exchange, new ResponseDTO(200, room));
        } catch (RuntimeException e) {
            ResponseUtils.sendResponse(exchange, new ResponseDTO(404, e.getMessage()));
        }
    }

    private void create(HttpExchange exchange) throws IOException {
        Room room = parseRequest(exchange);
        Room created = roomService.createRoom(room);
        ResponseUtils.sendResponse(exchange, new ResponseDTO(201, created));
    }

    private void update(HttpExchange exchange) throws IOException {
        Room room = parseRequest(exchange);
        Room updated = roomService.updateRoom(room);
        ResponseUtils.sendResponse(exchange, new ResponseDTO(200, updated));
    }

    private void delete(HttpExchange exchange, Long id) throws IOException {
        roomService.deleteRoom(id);
        ResponseUtils.sendResponse(exchange, new ResponseDTO(204, -1));
    }

    private Room parseRequest(HttpExchange exchange) throws IOException {
        String body = readBody(exchange);
        Long id = parseLong(HelperUtils.extractJsonValue(body, "id"));
        String roomNumber = HelperUtils.extractJsonValue(body, "roomNumber");
        String type = HelperUtils.extractJsonValue(body, "type");
        Double price = Double.parseDouble(HelperUtils.extractJsonValue(body, "price"));
        Integer capacity = Integer.parseInt(HelperUtils.extractJsonValue(body, "capacity"));

        Room room = new Room();
        room.setId(id);
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setPrice(price);
        room.setCapacity(capacity);
        return room;
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
