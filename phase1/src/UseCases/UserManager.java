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
        Organizer newOrganizer = new Organizer(pass, name);
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
        Speaker newSpeaker = new Speaker(pass, name);
        allSpeakers.put(name, newSpeaker);
        allUsers.put(name, newSpeaker);
    }

    /**
     * Returns true if name is not already in the list of users. Return false otherwise.
     * @param name The person we are trying to add
     * @return True is the person can be added and false otherwise
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

    /**
     * Returns true when the speaker can be added to an event
     * @param time The time of the event
     * @param events The list of events the speaker is speaking at
     * @return true when the speaker can be added to an event
     */
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
     * @param type The type of user they are
     * @return true if the password was correct, false otherwise
     */
    public boolean login(String name, String pass, String type){
        if (type.equals("attendee") && getAttendees().containsKey(name)){
            return pass.equals(getAttendee(name).getPassword());
        }
        if (type.equals("organizer") && getOrganizers().containsKey(name)){
            return pass.equals(getAttendee(name).getPassword());
        }
        if (type.equals("speaker") && getSpeakers().containsKey(name)){
            return pass.equals(getSpeaker(name).getPassword());
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

