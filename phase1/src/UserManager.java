import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;

public class UserManager {
    EventManager events;
    Hashtable<String, Attendee> allAttendees;
    Hashtable<String, Organizer> allOrganizers;
    Hashtable<String, Speaker> allSpeakers;
    public UserManager(EventManager events){
        this.events = events;
        allAttendees = new Hashtable<>();
        allOrganizers = new Hashtable<>();
        allSpeakers = new Hashtable<>();
    }

    public Hashtable<String, Attendee> getAllAttendees(){ return allAttendees;}

    public Attendee getAttendee(String name){ return allAttendees.get(name);}

    public void addAttendee(Attendee person){
        String name = person.getUsername();
        allAttendees.put(name, person);
    }

    public Hashtable<String, Organizer> getOrganizers(){ return allOrganizers;}

    public Organizer getOrganizer(String name){ return allOrganizers.get(name);}

    public void addOrganizer(Organizer person){
        String name = person.getUsername();
        allOrganizers.put(name, person);
    }

    public Hashtable<String, Speaker> getSpeakers(){ return allSpeakers;}

    public Speaker getSpeaker(String name){ return allSpeakers.get(name);}

    public void addSpeaker(Speaker person){
        String name = person.getUsername();
        allSpeakers.put(name, person);
    }

    // Helper method for signUp()
    // This is used to find what position the event should be added at in the list of the users events
    // They are ordered in chronological order based on time. I am using binary search here
    private int findPosOfEvent(int start, int end, LocalDateTime eventTime, List<Event> userEvents){
        if (start == end){
            return start;
        }
        int m = (start + end) / 2;
        LocalDateTime time = userEvents.get(m).getTime();
        if (eventTime.equals(time)){
            return -1;
        }
        else if (eventTime.compareTo(time) < 0){
            return findPosOfEvent(start, m, eventTime, userEvents);
        }
        else{
            return findPosOfEvent(m + 1, end, eventTime, userEvents);
        }
    }

    public boolean signUp(User person, Event eventName){
        if (!events.isFull(eventName)) {
            return false;
        }
        LocalDateTime eventTime = eventName.getTime();
        List<Event> userEvents = events.getUserEvents(person); //*** WAITING FOR EVENTMANAGER ***
        LocalDateTime timePlusDuration = eventTime.plusHours(eventName.getDuration());
        int numEvents = person.getEvents().size();

        if (numEvents == 0){
            person.addEvent(eventName.getEventName(), 0);
        }

        int pos = findPosOfEvent(0, numEvents, eventTime, userEvents);

        if (pos == -1){
            return false;
        }


        if (pos == 0){
            Event afterEvent = userEvents.get(pos);
            if (afterEvent.getTime().compareTo(timePlusDuration) < 0){
                return false;
            }
        }
        else if (pos == numEvents){
            Event behindEvent = userEvents.get(pos - 1);
            LocalDateTime behindEventTime = behindEvent.getTime().plusHours(behindEvent.getDuration());
            if (behindEventTime.compareTo(eventTime) > 0){
                return false;
            }
        }
        else {
            Event behindEvent = userEvents.get(pos - 1);
            Event afterEvent = userEvents.get(pos);
            LocalDateTime behindEventTime = behindEvent.getTime().plusHours(behindEvent.getDuration());
            if (afterEvent.getTime().compareTo(timePlusDuration) < 0 || behindEventTime.compareTo(eventTime) > 0){
                return false;
            }
        }
        person.addEvent(eventName.getEventName(), pos);
        events.addUserToEvent(person, eventName); //*** WAITING FOR EVENTMANAGER***
        return true;
    }

    public void cancelEvent(User person, Event canceledEvent){
        String eventName = canceledEvent.getEventName();
        person.removeEvent(eventName);
    }

    public void changePassword(User person, String pass){
        person.setPassword(pass);
    }
}

