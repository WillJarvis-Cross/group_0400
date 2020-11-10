import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;

public class UserManager {
    EventManager events;
    Hashtable<String, Attendee> allAttendees;
    Hashtable<String, Organizer> allOrganizers;
    Hashtable<String, Speaker> allSpeakers;
    Hashtable<String, User> allUsers;
    public UserManager(EventManager events){
        this.events = events;
        allAttendees = new Hashtable<>();
        allOrganizers = new Hashtable<>();
        allSpeakers = new Hashtable<>();
        allUsers = new Hashtable<>();
    }

    public Hashtable<String, Attendee> getAttendees(){ return allAttendees;}

    public Attendee getAttendee(String name){ return allAttendees.get(name);}

    public void addAttendee(String name, String pass){
        Attendee newAttendee = new Attendee(name, pass);
        allAttendees.put(name, newAttendee);
        allUsers.put(name, newAttendee);
    }

    public Hashtable<String, Organizer> getOrganizers(){ return allOrganizers;}

    public Organizer getOrganizer(String name){ return allOrganizers.get(name);}

    public void addOrganizer(String name, String pass){
        Organizer newOrganizer = new Organizer(name, pass);
        allOrganizers.put(name, newOrganizer);
        allUsers.put(name, newOrganizer);
    }

    public Hashtable<String, Speaker> getSpeakers(){ return allSpeakers;}

    public Speaker getSpeaker(String name){ return allSpeakers.get(name);}

    public void addSpeaker(String name, String pass){
        Speaker newSpeaker = new Speaker(name, pass);
        allSpeakers.put(name, newSpeaker);
        allUsers.put(name, newASpeaker);
    }

    public User getUser(String name){ return allUsers.get(name);}

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

    public void cancelMyEvent(User person, String canceledEvent){ // EventManager should have a method which calls this
        // which removes this users name from list of events
        person.removeEvent(canceledEvent);
    }

    public void cancelWholeEvent(List<String> attending, String canceledEvent, String speakerName){
        // EventManager should call this
        for (String name : attending){
            cancelMyEvent(getUser(name), canceledEvent);
        }
    }

    public boolean login(String name, String pass){
        User thisUser = getUser(name);
        return pass.equals(thisUser.getPassword());
    }

    public void changePassword(User person, String pass){
        person.setPassword(pass);
    }
}

