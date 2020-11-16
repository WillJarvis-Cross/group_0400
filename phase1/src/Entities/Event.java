package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This is an entity for a Event which contains the time of the event, the duration, the speaker, a list of
 * the people attending, the capacity of the event, the name of the event, and the room its in
 * @author group 0400
 */
public class Event {
    // Time is the time the event is scheduled for in the form [yyyy, mm, dd, hh, mm]
    private LocalDateTime time;
    private int duration; // The duration of the event (in hours)
    private String speaker; // The speaker at the event
    private List<String> attending; // List of people attending the event
    private String eventName; // The name of the event
    private String roomNumber; // The room the event is in
    private final DateTimeFormatter formatter; // The format that the time of the event is printed

    /**
     * Constructs an event with the given parameters. Also chooses format for how the time of the event is displayed
     * @param time The time the event occurs at
     * @param duration How long the event is in hours
     * @param speaker The speaker speaking at the event
     * @param eventName The name of the event
     * @param roomNumber The room the event is in
     */
    public Event(LocalDateTime time, int duration, String speaker, String eventName, String roomNumber) {
        this.time = time;
        this.duration = duration;
        this.speaker = speaker;
        this.attending = new ArrayList<>();
        this.eventName = eventName;
        this.roomNumber = roomNumber;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * Returns the time of the event in the form [yyyy, mm, dd, hh, mm]
     * @return the time of the event in the form [yyyy, mm, dd, hh, mm]
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Changes the time of the event to the time given by the input
     * @param time The new time of the event
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
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
     * Changes the speaker of this event to the name of the speaker inputted
     * @param speaker The name of the new speaker of the event
     */
    public void setSpeaker(String speaker){ this.speaker = speaker;}

    /**
     * Returns a string of the properties of the event formatted nicely
     * @return a string of the properties of the event formatted nicely
     */
    public String toString(){
        return "Event: "+ eventName+ "\nSpeaker: "+ speaker+ "\nDate: "+
                time.format(formatter)+ "\nDuration: "+ duration+ " hour"+"\nRoom: "+roomNumber;
    }
}