package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Set;

/** Represents the use case for Event objects
 * @author group 400
 */
public class EventManager {


    private Hashtable<String, Event> events;
    /**
     * Create an instance of eventManager with no scheduled events
     */
    public EventManager(){
        this.events = new Hashtable<String, Event>();
    }

    /**
     * Create an instance of eventManager with the events listed in the parameter
     * @param events
     */
    public EventManager(ArrayList<Event> events){
        for (Event event: events){
            this.events.put(event.getEventName(), event);
        }
    }

    /**
     * Checks if the event with the given name is at capacity
     * @param eventName
     * @return true if the event with the give eventName is at capacity, and false otherwise
     */
    public boolean isAtCapacity(String eventName, int capacity){
        Event event = this.events.get(eventName);
        return capacity <= event.getAttending().size();
    }

    /**
     * Checks if any other events with overlapping times have the same speaker or take place
     * in the same room
     * @param e
     * @return true if no event other than e taking place at the same time
     *         takes place at the same room or has the same speaker, and false otherwise
     */
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


    /**
     * This method returns an Event object with the given eventName
     * @param eventName
     * @return Event
     */
    public Event getEvent(String eventName){
        return this.events.get(eventName);
    }

    /**
     * Returns a list of all scheduled events
     * @return An ArrayList of events
     */
    //returns an arraylist of all scheduled events
    public ArrayList<Event> getEvents(){
        ArrayList<Event> eventList = new ArrayList<>();
        Set<String> keys = this.events.keySet();
        for (String key: keys) {
            eventList.add(getEvent(key));
        }
        return eventList;
    }

    /**
     * Returns a list of all scheduled events the user with the given useName
     * is registered in
     * @param userName
     * @return An ArrayList of events
     */
    public ArrayList<Event> getEventsByUsername(String userName){
        ArrayList<Event> eventList = getEvents();
        for (Event event: eventList){
            if (!event.getAttending().contains(userName)){
                eventList.remove(event);
            }
        }
        return eventList;
    }

    /**
     * Adds the person with the given username if
     *
     * @param userName The username of the user
     * @param eventName The name of the event to add the user to
     * @return true if successful, returns false
     */
    public void addPersonToEvent(String eventName, String userName){
        this.events.get(eventName).addAttending(userName);
    }

    public boolean canAddPerson(String eventName){
        return this.events.containsKey(eventName);
    }

    /**
     * Creates and stores an Event with the given parameters if it takes place
     * entirely between 9am and 5pm, and no other event taking place at the
     * same time has the same speaker or takes place at the same room.
     * @param time
     * @param duration
     * @param speaker
     * @param eName
     * @param room
     * @return true if the Event is created successfully, false otherwise
     */
    public void scheduleEvent(LocalDateTime time, int duration, String speaker, String eName, String room){
        Event e = new Event(time, duration, speaker, eName, room);
        this.events.put(eName, e);
    }

    public boolean canScheduleEvent(LocalDateTime time, int duration, String speaker, String eName, String room){
        Event e = new Event(time, duration, speaker, eName, room);
        return (!doesOverlap(e)) && (withinHours(e));
    }

    /**
     * Removes the event with the given name from Events if it exists
     * @param eventName
     * @return true if the event exists and is removed, returns false otherwise
     */
    public void removeEvent(String eventName){
        this.events.remove(eventName);
    }

    public boolean canRemoveEvent(String eventName){
        return events.containsKey(eventName);
    }

    /**
     * Changes the speaker of an event with the given name
     * @param eventName
     * @param speakerName
     */
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


