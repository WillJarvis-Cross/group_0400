package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Set;

/** Represents the use case for Event objects
 * @author group 400
 */
public class EventManager {


    private Hashtable<String, Event> events; //
    /**
     * Create an instance of eventManager with no scheduled events
     */
    public EventManager(){
        this.events = new Hashtable<>();
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
     * @param eventName The name of the event
     * @param capacity How many people are allowed at the event
     * @return true if the event with the give eventName is at capacity, and false otherwise
     */
    public boolean isAtCapacity(String eventName, int capacity){
        Event event = this.events.get(eventName);
        return capacity <= event.getAttending().size();
    }

    /**
     * Checks if any other events with overlapping times have the same speaker or take place
     * in the same room
     * @param e The event it is checking to make sure it doesn't overlap with
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
     * @param eventName The name of the event it returns
     * @return Event
     */
    public Event getEvent(String eventName){
        return this.events.get(eventName);
    }

    /**
     * Returns a list of all scheduled events
     * @return An ArrayList of events
     */
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
     * @param userName The name of the user
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
     * Returns a list of all scheduled events the speaker with the given speakerName
     * is registered in
     * @param speakerName The name of the speaker.
     * @return An ArrayList of events that the speakerName is speaking at.
     */
    public ArrayList<Event> getEventsBySpeaker(String speakerName){
        ArrayList<Event> eventList = new ArrayList<Event> ();
        for (Event event: getEvents()){
            if (event.getSpeaker() == speakerName){
                eventList.add(event);
            }
        }
        return eventList;
    }

    /**
     * Adds the person with the given username to the given event
     *
     * @param userName The username of the user
     * @param eventName The name of the event to add the user to
     */
    public void addPersonToEvent(String eventName, String userName){
        this.events.get(eventName).addAttending(userName);
    }

    /**
     * Returns true when a person can be added to the event. Return false when they can't.
     * @param eventName The name of the event the person is being added to
     * @return true or false
     */
    public boolean canAddPerson(String eventName){
        return this.events.containsKey(eventName);
    }

    public boolean removeAttendee(String eventName, String username){
        if (!events.containsKey(eventName)){
            return false;
        }
        Event thisEvent = getEvent(eventName);
        if (getEvent(eventName).getAttending().contains(username)){
            thisEvent.removeAttending(username);
            return true;
        }
        return false;
    }

    /**
     * Creates and stores an Event with the given parameters if it takes place
     * entirely between 9am and 5pm, and no other event taking place at the
     * same time has the same speaker or takes place at the same room.
     *
     * @param time The time the event is occurring at
     * @param duration How long the event is in hours
     * @param speaker The speaker at the event
     * @param eName The name of the event
     * @param room The name of the room which hosts the event
     */
    public void scheduleEvent(LocalDateTime time, int duration, String speaker, String eName, String room){
        Event e = new Event(time, duration, speaker, eName, room);
        this.events.put(eName, e);
    }

    /**
     * Returns true when the inputted event can be scheduled without overlapping with any other events. Returns false
     * otherwise
     * @param time The time of the inputted event
     * @param duration The duration of the event in hours
     * @param speaker The speaker at the event
     * @param eName The name of the event
     * @param room The name of the room which hosts the event
     * @return True or false
     */
    public boolean canScheduleEvent(LocalDateTime time, int duration, String speaker, String eName, String room){
        Event e = new Event(time, duration, speaker, eName, room);
        return (!doesOverlap(e)) && (withinHours(e));
    }

    /**
     * Removes the event with the given name from Events
     * @param eventName The name of the event
     */
    public void removeEvent(String eventName){
        this.events.remove(eventName);
    }

    /**
     * Returns true when the event can be removed without causing an error. Return false otherwise.
     * @param eventName The name of the event
     * @return true or false
     */
    public boolean canRemoveEvent(String eventName){
        return events.containsKey(eventName);
    }

    /**
     * Changes the speaker of an event with the given name
     * @param eventName The name of the event
     * @param speakerName The name of the new speaker at this event
     */
    public void setSpeaker(String eventName, String speakerName){
        events.get(eventName).setSpeaker(speakerName);
    }

    //returns true if the event occurs entirely within conference hours
    //we are assuming all events start on the hour

    /**
     * Returns true if the event occurs between 9am and 5pm. Returns false otherwise.
     * @param e The event in which this method is checking the time of
     * @return true or false
     */
    private boolean withinHours(Event e){
        int hour = e.getTime().getHour();
        return (hour > 7) && (hour + e.getDuration() < 18);
    }

    public String getEventToString(String eventName){
        if (events.containsKey(eventName)){
            return getEvent(eventName).toString();
        }
        return "";
    }
}


