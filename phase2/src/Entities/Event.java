package Entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This is an entity for a Event which contains the time of the event, the duration, the speaker, a list of
 * the people attending, the capacity of the event, the name of the event, and the room its in
 * @author group 0400
 */
public class Event implements Serializable {
    // Time is the time the event is scheduled for in the form yyyy-mm-ddThh:mm
    private String time;
    private int duration; // The duration of the event (in hours)
    private String speaker; // The speaker at the event
    private List<String> attending; // List of people attending the event
    private String eventName; // The name of the event
    private String roomNumber; // The room the event is in
    private int capacity; // The maximum capacity of the event


    /**
     * Constructs an event with the given parameters.
     * @param time The time the event occurs at
     * @param duration How long the event is in hours
     * @param speaker The speaker speaking at the event
     * @param eventName The name of the event
     * @param roomNumber The room the event is in
     * @param capacity The capacity of the event
     */
    public Event(String time, int duration, String speaker, String eventName, String roomNumber, int capacity) {
        this.time = time;
        this.duration = duration;
        this.speaker = speaker;
        this.attending = new ArrayList<>();
        this.eventName = eventName;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    /**
     * Returns the time of the event in the LocalDateTime object
     * @return the time of the event in the LocalDateTime object
     */
    public LocalDateTime getTime() {
        return LocalDateTime.parse(time);
    }

    /**
     * Changes the time of the event to the time given by the input
     * @param time The new time of the event
     */
    public void setTime(LocalDateTime time) {
        this.time = time.toString();
    }

    /**
     * Returns the name of the speaker of the event
     * @return the name of the speaker of the event
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Returns the list of names of people attending the event
     * @return the list of names of people attending the event
     */
    public List<String> getAttending() {
        return attending;
    }

    /**
     * Adds the name of a user to the list of people attending this event
     * @param newAttending  The name of the person being added to the event
     */
    public void addAttending(String newAttending) {
        attending.add(newAttending);
    }

    /**
     * Removes the given string from the list of attending people
     * @param name The person being removed from the list
     */
    public void removeAttending(String name){attending.remove(name);}

    /**
     * Returns the name of the event
     * @return the name of the event
     */
    public String getEventName(){ return eventName;}

    /**
     * Returns the duration of the event in hours
     * @return the duration of the event in hours
     */
    public int getDuration(){ return duration;}

    /**
     * Returns the room this event is in
     * @return the room this event is in
     */
    public String getRoomNum(){ return roomNumber;}

    /**
     * Returns the capacity of the event
     * @return the capacity
     */
    public int getCapacity(){ return capacity;}

    /**
     * Sets the new capacity of the event
     * @param capacity capacity of the event
     */
    public void setCapacity(int capacity){ this.capacity = capacity;}

    /**
     * Changes the speaker of this event to the name of the speaker inputted
     * @param speaker The name of the new speaker of the event
     */
    public void setSpeaker(String speaker){ this.speaker = speaker;}

    /**
     * Returns a string of the properties of the event formatted nicely
     * @return a string of the properties of the event formatted nicely
     */
    public String toString(){
        // The format that the time of the event is printed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Event: "+ eventName+ "\nSpeaker: "+ speaker+ "\nDate: "+
                getTime().format(formatter)+ "\nDuration: "+ duration+ " hour"+"\nRoom: "+roomNumber;
    }
}