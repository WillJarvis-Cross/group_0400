package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Set;

public class EventManager {

    private Hashtable<String, Event> events;

    public EventManager(){
        this.events = new Hashtable<String, Event>();
    }

    public EventManager(ArrayList<Event> events){
        for (Event event: events){
            this.events.put(event.getEventName(), event);
        }
    }

    //returns true or false if the event is or is not at capacity, respectively.
    //If the event of the given name is not found, it will return true
    public boolean isAtCapacity(String eventName){
        Event event = this.events.get(eventName);
        if (event.getCapacity() == event.getAttending().size()){
            return true;
        } else {
            return false;
        }
    }

    //returns true if e overlaps with another event taking place in the same room or the speaker of e is already giving
    // a talk when e is taking place, returns false otherwise
    //all talks are one hour long and begin at the hour for phase 1
    public boolean doesOverlap(Event e){
        Set<String> keys = this.events.keySet();
        for (String key: keys){
            Event event = getEvent(key);
            if (event.getTime().equals(e.getTime())){
                if ((event.getRoomNum().equals(e.getRoomNum()))||event.getSpeaker().equals(e.getSpeaker())) {
                    return true;
                }
            }
        }
        return false;
    }

    //returns an event with the given eventName
    public Event getEvent(String eventName){
        return this.events.get(eventName);
    }

    //returns an arraylist of all scheduled events
    public ArrayList<Event> getEvents(){
        ArrayList<Event> eventList = new ArrayList<Event>();
        Set<String> keys = this.events.keySet();
        for (String key: keys) {
            eventList.add(getEvent(key));
        }
        return eventList;
    }

    //returns an arrayList of all events scheduled by a user with the given userName
    public ArrayList<Event> getEventsByUsername(String userName){
        ArrayList<Event> eventList = getEvents();
        for (Event event: eventList){
            if (!event.getAttending().contains(userName)){
                eventList.remove(event);
            }
        }
        return eventList;
    }

    //adds userName to the list of attendees of an event with eventName and returns true if such a name exists, returns
    //false otherwise
    public boolean addPersonToEvent(String eventName, String userName){

        if (!this.events.containsKey(eventName)){
            return false;
        } else {
            this.events.get(eventName).getAttending().add(userName);
            return true;
        }
    }

    //schedules an event with the given properties given that it does not overlap with another talk given in the same
    //room or by the same speaker, and takes place entirely between 9am and 5pm
    //returns true if event was added, return false otherwise
    public boolean scheduleEvent(LocalDateTime time, int duration, String speaker, int capacity, String eName, String room){
        Event e = new Event(time, duration, speaker, capacity, eName, room);
        if ((!doesOverlap(e))&&(withinHours(e))){
            this.events.put(eName, e);
            return true;
        }
        return false;
    }

    //removes an event with the givin eventName and returns true if it exists, returns false otherwise
    public boolean removeEvent(String eventName){
        if( this.events.containsKey(eventName) ){
            this.events.remove(eventName);
            return true;
        } else {
            return false;
        }
    }

    public void setSpeaker(String eventName, String speakerName){
        events.get(eventName).setSpeaker(speakerName);
    }

    //returns true if the event occurs entirely within conference hours
    //we are assuming all events start on the hour
    private boolean withinHours(Event e){
        int hour = e.getTime().getHour();
        return (hour > 7) && (hour + e.getDuration() < 18);
    }

}


