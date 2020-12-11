package UseCases;
import Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/** Represents the use case for Event objects
 * @author group 400
 */
public class EventManager implements Serializable {


    private Hashtable<String, Event> events; // Hashtable of the event name and its corresponding event
    /**
     * Create an instance of eventManager with no scheduled events
     */
    public EventManager(){
        this.events = new Hashtable<>();
    }

    /**
     * Create an instance of eventManager with the events listed in the parameter
     * @param events The list of events
     */
    public EventManager(List<Event> events){
        for (Event event: events){
            this.events.put(event.getEventName(), event);
        }
    }

    /**
     * Returns an arraylist with the name of every event
     * @return an arraylist with the name of every event
     */
    public List<String> getAllEventsString(){
        return new ArrayList<>(events.keySet());
    }

    /**
     * Checks if the event with the given name is at capacity
     * @param eventName The name of the event
     * @return true if the event with the give eventName is at capacity, and false otherwise
     */
    public boolean isAtCapacity(String eventName){
        Event event = this.events.get(eventName);
        return event.getCapacity() <= event.getAttending().size();
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
            if ((event.getRoomNum().equals(e.getRoomNum()))){
                LocalDateTime t1 = e.getTime();
                LocalDateTime f1 = t1.plusHours(e.getDuration());
                LocalDateTime t2 = event.getTime();
                LocalDateTime f2 = t2.plusHours(event.getDuration());
                if (((t1.compareTo(f2)<=0)&&(f1.compareTo(t2)>0))&&((t1.compareTo(f2)<0)&&(f1.compareTo(t2)>=0))){
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
    public List<Event> getEvents(){
        List<Event> eventList = new ArrayList<>();
        Set<String> keys = this.events.keySet();
        for (String key: keys) {
            eventList.add(getEvent(key));
        }
        return eventList;
    }

    /**
     * returns whether or not an events exists
     * @param eventName The event we are checking
     * @return true or false
     */
    public boolean containsEvent(String eventName){
        return events.containsKey(eventName);
    }

    /**
     * Returns a list of all scheduled events the user with the given useName
     * is registered in
     * @param person the person whose events it is returning
     * @return An ArrayList of events
     */
    public List<Event> getEventsByUsername(User person){
        List<Event> eventList = new ArrayList<>();
        for (String e: person.getEvents()){
            eventList.add(events.get(e));
        }
        return eventList;
    }

    /**
     * Returns a list of all scheduled events the user with the given useName is registered
     * in except for one given event
     * @param person The person whose events it is returning
     * @param event The event we are not including in the arraylist
     * @return An ArrayList of events
     */
    public List<Event> getEventsExceptOne(User person, Event event){
        List<Event> eventList = new ArrayList<>();
        for (String e: person.getEvents()){
            eventList.add(events.get(e));
        }
        eventList.remove(event);
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
     * If the given attendee can be removed from the given event then it does so and returns true
     * Otherwise it returns false
     * @param eventName The event in question
     * @param username The user being removed from the above event
     * @return true when the user is removed and false otherwise
     */
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
     * @param capacity The capacity of the event
     * @param price The price of the event
     */
    public void scheduleEvent(LocalDateTime time, int duration, List<String> speaker, String eName, String room, int capacity,boolean VIPcheck, int techLevel, double price){
        Event e = new Event(time.toString(), duration, speaker, eName, room, capacity,VIPcheck, techLevel, price);
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
     * @param capacity The capacity of the event
     * @param price The price of the event
     * @return True or false
     */
    public boolean canScheduleEvent(LocalDateTime time, int duration, List<String> speaker, String eName, String room,
                                    int capacity,boolean VIPcheck, int techLevel, double price){
        Event e = new Event(time.toString(), duration, speaker, eName, room, capacity,VIPcheck, techLevel, price);
        return (!doesOverlap(e)) && (withinHours(e) && !containsEvent(eName));
    }

    /**
     * Removes the event with the given name from Events
     * @param eventName The name of the event
     */
    public void removeEvent(String eventName){
        this.events.remove(eventName);
    }

    /**
     * Changes the speaker of an event with the given name
     * @param eventName The name of the event
     * @param speakerName The name of the new speaker to be added to the evnt
     */
    public void setSpeaker(String eventName, String speakerName){
        events.get(eventName).addSpeaker(speakerName);
    }

    /**
     * Prompts user to enter which user they want removed from a specific event
     * @param eventName The name of the evnt
     * @param speakerName the name of speaker to be added to event
     */

    public void removeSpeaker(String eventName, String speakerName){
        events.get(eventName).removeSpeaker(speakerName);
    }


    /**
     * Returns true if the event occurs between 9am and 5pm. Returns false otherwise.
     * @param e The event in which this method is checking the time of
     * @return true or false
     */
    private boolean withinHours(Event e){
        int hour = e.getTime().getHour();
        return (hour > 7) && (hour + e.getDuration() < 18);
    }

    /**
     * Returns the string representation of the event with the given name.
     * @param eventName The name of the event
     * @return String
     */
    public String getEventToString(String eventName){
        if (events.containsKey(eventName)){
            return getEvent(eventName).toString();
        }
        return "";
    }

    /**
     * Return the times of given event
     * @param eventName - event whose time we want
     * @return LocalDateTime
     */

    public LocalDateTime getEventtime(String eventName){
        return getEvent(eventName).getTime();
    }

    /**
     * Return the duration of given event
     * @param eventName - event whose time we want
     * @return int
     */
   public int getEventDuration(String eventName){
        return getEvent(eventName).getDuration();

   }


}


