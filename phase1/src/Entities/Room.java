package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Hashtable;

/** This is an entity for a Room which contains the name of the room, the room's capacity, and the events that are
 * in the room
 * @author group 0400
 */
public class Room implements Serializable {

    private String roomNumber; // The room number of the room

    // The Events that is in the room and what time the events are happening
    private Hashtable<String, String> events;
    private int capacity; // The number of attendees allowed in the room

    /**
     * Constructs a room given its name and its capacity. It also initializes a hashtable of events in the room
     * @param roomNumber The name of the room
     * @param capacity The maximum amount of attendees allowed in the room
     */
    public Room(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.events = new Hashtable<>();
    }

    /**
     * Returns the LocalDateTime object refering to the string
     * @return The LocalDateTime object refering to the string
     */
    public LocalDateTime getTime(String time) {
        return LocalDateTime.parse(time);
    }

    /**
     * Checks if an inputted time coincides with the time of an event in this room
     * @param time The time we are checking to make sure it doesn't interfere with any events
     * @return true or false: true is for when the time is taken
     */
    public boolean isTimeTaken(LocalDateTime time) {
        boolean timeTaken = false;
        for (String event : events.keySet())
        {
            if (getTime(events.get(event)) == time)
            {
                timeTaken = true;
            }
        }

        return timeTaken;
    }

    /**
     * Adds an event to the hashtable of events
     * @param eventName The new event being added to the hashtable
     * @param time The time of this new event
     */
    public void addEvent(String eventName, LocalDateTime time) {
        events.put(eventName, time.toString());
    }

    /**
     * Removes the inputted event from the hashtable of events
     * @param eventName The event being removed from the hashtable of events
     */
    public void removeEvent(String eventName) {
        events.remove(eventName);
    }

    /**
     * Returns the name of this room
     * @return the name of this room
     */
    public String getRoomNumber(){ return roomNumber;}

    /**
     * Returns the capacity of this room
     * @return the capacity of this room
     */
    public int getCapacity(){ return capacity;}
}
