package UseCases;
import Entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;
/** Represents the use case for Room objects
 * @author group 400
 */
public class UserManager implements Serializable {
    // hashtable where the key is the name of the attendee and the value is the attendee
    Hashtable<String, Attendee> allAttendees;

    // hashtable where the key is the name of the organizer and the value is the organizer
    Hashtable<String, Organizer> allOrganizers;

    // hashtable where the key is the name of the speaker and the value is the speaker
    Hashtable<String, Speaker> allSpeakers;

    // hashtable where the key is the name of the user and the value is the user
    Hashtable<String, User> allUsers;

    /**
     * Instantiates a UserManager with no users of any kind registered
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
     * @param name The name of the attendee
     * @return Attendee object corresponding to name
     */
    public Attendee getAttendee(String name){ return allAttendees.get(name);}

    /**
     * Creates a new Attendee with the given username and password
     * @param name The name of the new Attendee
     * @param pass The password of the attendee
     */
    public void addAttendee(String name, String pass){
        Attendee newAttendee = new Attendee(pass, name);
        allAttendees.put(name, newAttendee);
        allUsers.put(name, newAttendee);
    }
    public void addAttendee(Attendee newAttendee){
        allAttendees.put(newAttendee.getUsername(), newAttendee);
        allUsers.put(newAttendee.getUsername(), newAttendee);
    }

    /**
     * Returns a Hashtable of all Organizers
     * @return allOrganizers
     */
    public Hashtable<String, Organizer> getOrganizers(){ return allOrganizers;}

    /**
     * Returns an Organizer with the given username
     * @param name The name of the organizer
     * @return Organizer corresponding to name
     */
    public Organizer getOrganizer(String name){ return allOrganizers.get(name);}

    /**
     * Creates a new Organizer with the given username and password
     * @param name The name of the new organizer
     * @param pass The password of the new organizer
     */
    public void addOrganizer(String name, String pass){
        Organizer newOrganizer = new Organizer(name, pass);
        allOrganizers.put(name, newOrganizer);
        allUsers.put(name, newOrganizer);
    }
    public void addOrganizer(Organizer newOrganizer){
        allOrganizers.put(newOrganizer.getUsername(), newOrganizer);
        allUsers.put(newOrganizer.getUsername(), newOrganizer);
    }

    /**
     * Returns a Hashtable of all Speakers
     * @return allOrganizers
     */
    public Hashtable<String, Speaker> getSpeakers(){ return allSpeakers;}

    /**
     * Returns a Speaker with the given name
     * @param name The name of the speaker
     * @return Speaker corresponding to name
     */
    public Speaker getSpeaker(String name){ return allSpeakers.get(name);}

    /**
     * Creates a new Speaker with the given usename and password
     * @param name The name of the new speaker
     * @param pass The password of the new speaker
     */
    public void addSpeaker(String name, String pass){
        Speaker newSpeaker = new Speaker(name, pass);
        allSpeakers.put(name, newSpeaker);
        allUsers.put(name, newSpeaker);
    }
    public void addSpeaker(Speaker newSpeaker){
        allSpeakers.put(newSpeaker.getUsername(), newSpeaker);
        allUsers.put(newSpeaker.getUsername(), newSpeaker);
    }

    /**
     * Returns true if name is not already in the list of users. Return false otherwise.
     * @param name
     * @return
     */
    public boolean canAddPerson(String name){
        return !allUsers.containsKey(name);
    }

    /**
     * Returns a User with the given username
     * @param name The name of the user
     * @return User Corresponding to name
     */
    public User getUser(String name){ return allUsers.get(name);}

    /**
     * Returns a Hashtable of all Users
     * @return allUsers
     */
    public Hashtable<String, User> getUsers(){ return allUsers;}

    public boolean canAddSpeaker(LocalDateTime time, List<Event> events){
        for (Event event: events){
            if (event.getTime().equals(time)){
                return false;
            }
        }
        return true;
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
        if (eventTime.compareTo(time) < 0){
            return findPosOfEvent(start, m, eventTime, userEvents);
        }
        else{
            return findPosOfEvent(m + 1, end, eventTime, userEvents);
        }
    }

    /**
     * Adds the inputted event to the list of the user's event
     * @param name The name of the user
     * @param event The event being signed up for
     * @param myEvents The list of the user's events
     */
    public void signUp(String name, Event event, List<Event> myEvents){
        String eventName = event.getEventName();

        LocalDateTime eventTime = event.getTime();

        User person = getUser(name);
        int numEvents = myEvents.size();
        // pos is the position the event is being added in th e user's list of events

        int pos = findPosOfEvent(0, numEvents, eventTime, myEvents);

        person.addEvent(eventName, pos);
    }

    /**
     * Returns true if the user is available to sign up for the inputted event
     * @param time The time of the
     * @param myEvents The user's list of events
     * @return True if the user is available to sign up for the event. Return false otherwise.
     */
    public boolean canSignUp(LocalDateTime time, List<Event> myEvents){
        for (Event e:myEvents){
            if (e.getTime().equals(time)){
                return false;
            }
        }
        return true;

        /*String eventName = event.getEventName();

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
        return true;*/
    }

    /**
     * Cancels the person's spot in the event with the given name
     * @param person The name of the person
     * @param canceledEvent The name of the event person is canceling
     */
    public boolean cancelMyEvent(String person, String canceledEvent){
        User thisPerson = getUser(person);
        if (thisPerson.getEvents().contains(canceledEvent)){
            thisPerson.removeEvent(canceledEvent);
            return true;
        }
        return false;
    }



    /**
     * If the given event was cancelled successfully, it will remove all attendees and the
     * speaker from their respective lists of events
     * @param attending The list of the names of the attendees attending the event
     * @param canceledEvent The event getting canceled
     * @param speakerName The name of the speaker of the event
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
     * @param name The username
     * @param pass The password
     * @return true if the password was correct, false otherwise
     */
    public boolean login(String name, String pass){
        if (getUsers().containsKey(name)){
            return pass.equals(getUser(name).getPassword());
        }
        return false;
    }

    /**
     * Changes the person's password
     * @param person The name of the user
     * @param pass The user's password
     */
    public void changePassword(String person, String pass){
        allUsers.get(person).setPassword(pass);
    }
}

