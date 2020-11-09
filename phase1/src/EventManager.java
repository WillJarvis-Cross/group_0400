package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventManager {

    private ArrayList<Event> eventList;

    public EventManager(){
        this.eventList = new ArrayList();
    }

    public EventManager(ArrayLisyList<Event> events){
        this.eventList = events;
    }

    //returns true or false if the event is or is not at capacity, respectively.
    //If the event of the given name is not found, it will return true
    public boolean isAtCapacity(String eventName){
        Event event = getByName(eventName);
        if (event.getCapacity() == event.getAttending().length()){
            return true;
        } else {
            return false;
        }
    }

    //returns true if e overlaps with another event taking place in the same room or the speaker of e is already giving
    // a talk when e is taking place, returns false otherwise
    //all talks are one hour long and begin at the hour for phase 1
    public boolean doesOverlap(Event e){
        for (Event event: eventList){
            if (event.getTime().equals(e.getTime())){
                if ((event.getRoomNum().equals(e.getRoomNum()))||event.getSpeaker().equals(e.getSpeaker)) {
                    return true;
                }
            }
        }
        return false;
    }

    //schedules an event with the given properties given that it does not overlap with another talk given in the same
    //room or by the same speaker, and takes place entirely between 9am and 5pm
    public void scheduleEvent(LocalDateTime time, int duration, String speaker, int capacity, String event, int room){
        Event e = new Event(time, duration, speaker, capacity, event, room);
        if ((!doesOverlap(e))&&(withinHours(e)){
            this.eventList.add(e);
        }
    }

    //removes an event with the givin eventName
    public void removeEvent(String: eventName){
        Event event = getByName(eventName);
        this.eventList.remove(event);
    }

    //returns an event with the given eventName
    private Event getByName(String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
    }

    //returns true if the event occurs entirely within conference hours
    //we are assuming all events start on the hour
    private boolean withinHours(Event e){
        int hour = e.getTime().getHour();
        if ((hour>7) && (hour+e.getDuration()<18)){
            return true;
        } else {
            return false;
        }
    }
}

