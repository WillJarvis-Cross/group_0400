import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;

public class UserManager {
    EventManager events;
    Hashtable<String, Attendee> attendees;
    Hashtable<String, Organizer> organizers;
    Hashtable<String, Speaker> speakers;
    public UserManager(EventManager events){
        this.events = events;
        attendees = new Hashtable<>();
        organizers = new Hashtable<>();
        speakers = new Hashtable<>();
    }

    public Hashtable<String, Attendee> getAttendees(){ return attendees;}

    public void addAttendee(Attendee person){
        String name = person.getUsername();
        attendees.put(name, person);
    }

    public Hashtable<String, Organizer> getOrganizers(){ return organizers;}

    public void addOrganizer(Organizer person){
        String name = person.getUsername();
        organizers.put(name, person);
    }

    public Hashtable<String, Speaker> getSpeakers(){ return speakers;}

    public void addSpeaker(Speaker person){
        String name = person.getUsername();
        speakers.put(name, person);
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
        LocalDateTime eventTime = eventName.getTime();
        List<Event> userEvents = events.getUserEvents(person);
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
        return true;
    }

    public void cancelEvent(User person, Event canceledEvent){
        String eventName = canceledEvent.getEventName();
        person.removeEvent(eventName);
    }
}

