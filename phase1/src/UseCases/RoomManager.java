package UseCases;
import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;
/** Represents the use case for Room objects
 * @author group 400
 */

public class RoomManager {

    // The room numbers with the corresponding room
    public Hashtable<String, Room> allRooms;

    /**
     * Creates an instance of RoomManager with no rooms stored
     */
    public RoomManager() {
        allRooms = new Hashtable<>();
    }

    public Room getRoom(String roomName){
        if (allRooms.containsKey(roomName)){
            return allRooms.get(roomName);
        }
        return null;
    }

    /**
     * Creates a room with the given room number and capacity
     * @param roomNumber
     * @param capacity
    */
    public void addRoom(String roomNumber, int capacity) {
        allRooms.put(roomNumber, new Room(roomNumber, capacity));
    }

    /**
     * Checks if the room is booked for a certain time
     * @param roomNumber
     * @param time
     * @return true if the room is taken at the given time, false otherwise
     */
    public boolean isRoomtaken(String roomNumber, LocalDateTime time) {
        return allRooms.get(roomNumber).isTimeTaken(time);
    }

    /**
     * Adds an event to the room
     * @param roomNumber
     * @param eventName
     * @param time
     */
    public void addEvent(String roomNumber, String eventName, LocalDateTime time) {
        allRooms.get(roomNumber).addEvent(eventName, time);
    }

    /**
     * Removes an event from the room
     * @param roomNumber
     * @param eventName
     */
    public void removeEvent(String roomNumber, String eventName) {
        allRooms.get(roomNumber).removeEvent(eventName);
    }

}
