package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class Event {
    // Time is the time the event is scheduled for in the form [yyyy, mm, dd, hh, mm]
    private List<Integer> time;

    // The speaker at the event
    private final Speaker speaker;

    // List of people attending the event
    private List<User> attending;

    // Max number of people allowed at the event
    private final int capacity;

    public Event(List<Integer> time, Speaker speaker, int capacity) {
        this.time = time;
        this.speaker = speaker;
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

    // Returns the speaker
    public Speaker getSpeaker() {
        return speaker;
    }

    // Return a list of people attending the event
    public List<User> getAttending() {
        return attending;
    }

    // Adds a new user to the list of people attending the event
    public void addAttending(User newAttending) {
        attending.add(newAttending);
    }

    //returns the capacity of the event
    public int getCapacity(){
        return capacity;
    }
}