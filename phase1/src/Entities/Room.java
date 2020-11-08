package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class Room {

    // The room number of the room
    private String roomNumber;

    // The Events that is in the room and what time the events are happening
    Hashtable<String, LocalDateTime> events;

    // The number of attendees allowed in the room
    private int capacity;

    // The format that the time of the event is printed
    private final DateTimeFormatter formatter;

    public Room(String roomNumber, int capactiy) {
        this.roomNumber = roomNumber;
        this.capacity = capactiy;
        this.events = new Hashtable<>();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    // Checks if the time slot for the room is taken
    public boolean isTimeTaken(LocalDateTime time) {
        boolean timeTaken = false;
        for (String event : events.keySet())
        {
            if (events.get(event) == time)
            {
                timeTaken = true;
            }
        }

        return timeTaken;
    }

    // Adds an event to the room, this should be done after we check the event is valid
    public void addEvent(String eventName, LocalDateTime time) {
        events.put(eventName, time);
    }


    // Removes an event from the room
    public void removeEvent(String eventName) {
        events.remove(eventName);
    }
}
