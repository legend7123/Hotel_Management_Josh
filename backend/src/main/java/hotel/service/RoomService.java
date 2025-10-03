package service;

import model.Room;
import java.util.List;
import dao.RoomDAO;

public class RoomService {
    private final RoomDAO roomDao;

    public RoomService(RoomDAO roomDao) {
        this.roomDao = roomDao;
    }

    public Room createRoom(Room room) {
        return roomDao.create(room);
    }

    public List<Room> getAllRooms() {
        return roomDao.findAll();
    }

    public Room getRoom(Long id) {
        Room room = roomDao.findById(id);
        if (room == null) throw new RuntimeException("Room not found");
        return room;
    }

    public Room updateRoom(Room room) {
        return roomDao.update(room);
    }

    public void deleteRoom(Long id) {
        roomDao.delete(id);
    }
}
