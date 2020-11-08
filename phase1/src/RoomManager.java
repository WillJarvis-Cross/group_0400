import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;

public class RoomManager {

    // The room numbers with the corresponding room
    Hashtable<String, Room> allRooms;

    public RoomManager() {
        allRooms = new Hashtable<>();
    }

    // Adds a room to allRooms
    public void addRoom(String roomNumber, int capacity) {
        allRooms.put(roomNumber, new Room(roomNumber, capacity));
    }

    // Checks if the room is booked for a certain time
    public boolean isRoomtaken(String roomNumber, LocalDateTime time) {
        return allRooms.get(roomNumber).isTimeTaken(time);
    }

    // Adds an event to the room
    public void addEvent(String roomNumber, String eventName, LocalDateTime time) {
        allRooms.get(roomNumber).addEvent(eventName, time);
    }

    // Removes an event from the room
    public void removeEvent(String roomNumber, String eventName) {
        allRooms.get(roomNumber).removeEvent(eventName);
    }

}
