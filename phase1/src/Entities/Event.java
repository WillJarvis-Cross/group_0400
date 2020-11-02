package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class Event {
    // Time is the time the event is scheduled for in the form [yyyy, mm, dd, hh, mm]
    private List<Integer> time;

    // The username of the speaker at the event
    private final String speakerUsername;

    // List of people attending the event
    private List<String> attending;

    // Max number of people allowed at the event
    private final int capacity;

    public Event(List<Integer> time, String speakerUsername, int capacity) {
        this.time = time;
        this.speakerUsername = speakerUsername;
        this.attending = new ArrayList<>();
        this.capacity = capacity;
    }

    // Returns the time of the event in the form [yyyy, mm, dd, hh, mm]
    public List<Integer> getTime() {
        return time;
    }

    // Changes the time of the event
    public void setTime(List<Integer> time) {
        this.time = time;
    }

    // Returns the username of the speaker
    public String getSpeakerUsername() {
        return speakerUsername;
    }

    // Return a list of people attending the event
    public List<String> getAttending() {
        return attending;
    }

    // Adds a new user to the list of people attending the event
    public void addAttending(User newAttending) {
        attending.add(User.getUsername());
    }

    //returns the capacity of the event
    public int getCapacity(){
        return capacity;
    }
}