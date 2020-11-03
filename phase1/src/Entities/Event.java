package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event {
    // Time is the time the event is scheduled for in the form [yyyy, mm, dd, hh, mm]
    private LocalDateTime time;

    // The duration of the event (in hours)
    private int duration;

    // The speaker at the event
    private final Speaker speaker;

    // List of people attending the event
    private List<User> attending;

    // Max number of people allowed at the event
    private final int capacity;

    // The name of the event
    private String eventName;

    private final DateTimeFormatter formatter;

    public Event(LocalDateTime time, int duration, Speaker speaker, int capacity, String eventName) {
        this.time = time;
        this.duration = duration;
        this.speaker = speaker;
        this.attending = new ArrayList<>();
        this.capacity = capacity;
        this.eventName = eventName;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    // Returns the time of the event in the form [yyyy, mm, dd, hh, mm]
    public LocalDateTime getTime() {
        return time;
    }

    // Changes the time of the event
    public void setTime(LocalDateTime time) {
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

    // Returns the name of the event
    public String getEventName(){ return eventName;}

    public int getDuration(){ return duration;}

    public String toString(){
        return "Event: "+ eventName+ "\nSpeaker: "+ speaker.getUsername()+ "\nDate: "+
                time.format(formatter)+ "\nDuration: "+ duration+ " hour";
    }
}