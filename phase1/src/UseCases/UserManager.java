package UseCases;
import Entities.*;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;
/** Represents the use case for Room objects
 * @author group 400
 */
public class UserManager {

    Hashtable<String, Attendee> allAttendees;
    Hashtable<String, Organizer> allOrganizers;
    Hashtable<String, Speaker> allSpeakers;
    Hashtable<String, User> allUsers;

    /**
     * Instantiates a UserManager with no users of any kind registered
     * @param events
     */

    public UserManager(){
        allAttendees = new Hashtable<>();
        allOrganizers = new Hashtable<>();
        allSpeakers = new Hashtable<>();
        allUsers = new Hashtable<>();
    }

    /**
     * Returns a Hashtable of all Attendees
     * @return allAttendees
     */
    public Hashtable<String, Attendee> getAttendees(){ return allAttendees;}

    /**
     * Returns an Attendee with the given username
     * @param name
     * @return Attendee
     */
    public Attendee getAttendee(String name){ return allAttendees.get(name);}

    /**
     * Creates a new Attendee with the given username and password
     * @param name
     * @param pass
     */
    public void addAttendee(String name, String pass){
        Attendee newAttendee = new Attendee(name, pass);
        allAttendees.put(name, newAttendee);
        allUsers.put(name, newAttendee);
    }

    /**
     * Returns a Hashtable of all Oranizers
     * @return allOrganizers
     */
    public Hashtable<String, Organizer> getOrganizers(){ return allOrganizers;}

    /**
     * Returns an Organizer with the given username
     * @param name
     * @return Organizer
     */
    public Organizer getOrganizer(String name){ return allOrganizers.get(name);}

    /**
     * Creates a new Organizer with the given username and password
     * @param name
     * @param pass
     */
    public void addOrganizer(String name, String pass){
        Organizer newOrganizer = new Organizer(name, pass);
        allOrganizers.put(name, newOrganizer);
        allUsers.put(name, newOrganizer);
    }

    /**
     * Returns a Hashtable of all Speakers
     * @return allOrganizers
     */
    public Hashtable<String, Speaker> getSpeakers(){ return allSpeakers;}

    /**
     * Returns a Speaker with the given name
     * @param name
     * @return Speaker
     */
    public Speaker getSpeaker(String name){ return allSpeakers.get(name);}

    /**
     * Creates a new Speaker with the given usename and password
     * @param name
     * @param pass
     */
    public void addSpeaker(String name, String pass){
        Speaker newSpeaker = new Speaker(name, pass);
        allSpeakers.put(name, newSpeaker);
        allUsers.put(name, newSpeaker);
    }

    /**
     * Returns a User with the given username
     * @param name
     * @return User
     */
    public User getUser(String name){ return allUsers.get(name);}

    /**
     * Returns a Hashtable of all Users
     * @return allUsers
     */
    public Hashtable<String, User> getUsers(){ return allUsers;}


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

    /**
     * Adds a user with the given username to the event with the given name if possible
     * @param name
     * @param eventName
     * @return true if the user was added to an event and false otherwise
     */
    public void signUp(String name, Event event, List<Event> myEvents){
        String eventName = event.getEventName();

        LocalDateTime eventTime = event.getTime();

        User person = getUser(name);
        int numEvents = myEvents.size();

        int pos = findPosOfEvent(0, numEvents, eventTime, myEvents);

        person.addEvent(eventName, pos);
    }

    public boolean canSignUp(String name, Event event, List<Event> myEvents){
        String eventName = event.getEventName();

        LocalDateTime eventTime = event.getTime();

        LocalDateTime timePlusDuration = eventTime.plusHours(event.getDuration());
        User person = getUser(name);
        int numEvents = myEvents.size();

        if (numEvents == 0){
            person.addEvent(eventName, 0);
            return true;
        }

        int pos = findPosOfEvent(0, numEvents, eventTime, myEvents);

        if (pos == -1){
            return false;
        }


        if (pos == 0){
            Event afterEvent = myEvents.get(pos);
            if (afterEvent.getTime().compareTo(timePlusDuration) < 0){
                return false;
            }
        }
        else if (pos == numEvents){
            Event behindEvent = myEvents.get(pos - 1);
            LocalDateTime behindEventTime = behindEvent.getTime().plusHours(behindEvent.getDuration());
            if (behindEventTime.compareTo(eventTime) > 0){
                return false;
            }
        }
        else {
            Event behindEvent = myEvents.get(pos - 1);
            Event afterEvent = myEvents.get(pos);
            LocalDateTime behindEventTime = behindEvent.getTime().plusHours(behindEvent.getDuration());
            if (afterEvent.getTime().compareTo(timePlusDuration) < 0 || behindEventTime.compareTo(eventTime) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Cancels the person's spot in the event with the given name
     * @param person
     * @param canceledEvent
     */
    public void cancelMyEvent(String person, String canceledEvent){
        User thisPerson = getUser(person);
        if (thisPerson.getEvents().contains(canceledEvent)){
            thisPerson.removeEvent(canceledEvent);
        }
    }



    /**
     * If the given event was cancelled successfully, it will remove all attendees and the
     * speaker from their respective lists of events
     * @param attending
     * @param canceledEvent
     * @param speakerName
     */
    public void cancelWholeEvent(List<String> attending, String canceledEvent, String speakerName){
        for (String name : attending) {
            cancelMyEvent(name, canceledEvent);
        }
        cancelMyEvent(speakerName, canceledEvent);
    }

    /**
     * Allows the user with the given username to log in to their account if they
     * enter the correct password for their account
     * @param name
     * @param pass
     * @return true if the password was correct, false otherwise
     */
    public boolean login(String name, String pass){
        User thisUser = getUser(name);
        return pass.equals(thisUser.getPassword());
    }

    /**
     * Changes the person's password
     * @param person
     * @param pass
     */
    public void changePassword(User person, String pass){
        person.setPassword(pass);
    }
}

