package UseCases;
import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;

/** Represents the use case for Room objects
 * @author group 0400
 */
public class RoomManager {

    public Hashtable<String, Room> allRooms; // The room numbers with the corresponding room

    /**
     * Creates an instance of RoomManager with no rooms stored
     */
    public RoomManager() {
        allRooms = new Hashtable<>();
    }

    /**
     * Returns the room corresponding to roomName if it exists. Returns null otherwise.
     * @param roomName The name of the room the method is returning
     * @return The room object corresponding to roomName
     */
    public Room getRoom(String roomName){
        if (allRooms.containsKey(roomName)){
            return allRooms.get(roomName);
        }
        return null;
    }

    /**
     * Creates a room with the given room number and capacity
     * @param roomNumber The name of the room
     * @param capacity The capacity of the room
    */
    public void addRoom(String roomNumber, int capacity) {
        allRooms.put(roomNumber, new Room(roomNumber, capacity));
    }

    /**
     * Checks if the room is booked for a certain time
     * @param roomNumber The name of the room
     * @param time The time you are checking for
     * @return true if the room is taken at the given time, false otherwise
     */
    public boolean isRoomTaken(String roomNumber, LocalDateTime time) {
        return allRooms.get(roomNumber).isTimeTaken(time);
    }

    /**
     * Adds an event to the room
     * @param roomNumber The name of the room
     * @param eventName The name of the event
     * @param time The time of the event
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
