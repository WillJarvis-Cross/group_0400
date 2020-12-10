package UseCases;
import Entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

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

    // hashtable where the key is the name of the VIP and the value is the speaker
    Hashtable<String, VIP> allVIP;

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
        allVIP = new Hashtable<>();
    }
    /**
     * Returns a Hashtable of all Attendees
     * @return allAttendees
     */
    public Hashtable<String, VIP> getAllVIPs(){ return allVIP;}

    /**
     * Returns an Attendee with the given username
     * @param name The name of the attendee
     * @return Attendee object corresponding to name
     */
    public VIP getVIP(String name){ return allVIP.get(name);}

    /**
     * Creates a new Attendee with the given username and password
     * @param name The name of the new Attendee
     * @param pass The password of the attendee
     */
    public void addVIP(String name, String pass){
        VIP newVIP = new VIP(pass, name);
        allVIP.put(name, newVIP);
        allUsers.put(name, newVIP);
    }

    public boolean canAddSpeaker(String speaker){

        return allUsers.containsKey(speaker);
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

    public List<User> getSpeakerObjects(){
        List<User> speakers = new ArrayList<>();
        Set<String> keys = allSpeakers.keySet();
        for (String key: keys){
            speakers.add(allSpeakers.get(key));
        }
        return speakers;
    }

    public List<User> getAttendeeObjects(){
        List<User> attendees = new ArrayList<>();
        Set<String> keys = allAttendees.keySet();
        for (String key: keys){
            attendees.add(allAttendees.get(key));
        }
        return attendees;
    }

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

    public boolean canMakeGroupChat(String[] names){
        if (names.length == 0){
            return false;
        }
        for (String name: names){
            if (canAddPerson(name)){
                return false;
            }
        }
        return true;
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
    public void signUp(String name, Event event, List<Event> myEvents, double price){
        String eventName = event.getEventName();

        LocalDateTime eventTime = event.getTime();

        User person = getUser(name);
        int numEvents = myEvents.size();
        // pos is the position the event is being added in th e user's list of events

        int pos = findPosOfEvent(0, numEvents, eventTime, myEvents);
        person.removeFromBalance(price);
        person.addEvent(eventName, pos);
    }

    /**
     * Returns true if the user is available to sign up for the inputted event
     * @param time The time of the event
     * @param duration The duration of the event in hours
     * @param myEvents The user's list of events
     * @return True if the user is available to sign up for the event. Return false otherwise.
     */
    public boolean canSignUp(LocalDateTime time, int duration, List<Event> myEvents){
        for (Event e:myEvents){
            LocalDateTime t1 = e.getTime();
            LocalDateTime f1 = t1.plusHours(e.getDuration());
            LocalDateTime f2 = time.plusHours(duration);
            if (((t1.compareTo(f2)<=0)&&(f1.compareTo(time)>0))&&((t1.compareTo(f2)<0)&&(f1.compareTo(time)>=0))){
                return false;
            }
        }
        return true;
    }


    public boolean checkVIPSignUp(Event event,User user){
        if(event.isVIPOnly()) {
            if (user.isVIP()) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Cancels the person's spot in the event with the given name
     * @param person The name of the person
     * @param canceledEvent The name of the event person is canceling
     */
    public boolean cancelMyEvent(String person, String canceledEvent, double price){
        User thisPerson = getUser(person);
        if (thisPerson.getEvents().contains(canceledEvent)){
            thisPerson.removeEvent(canceledEvent);
            thisPerson.addToBalance(price);
            return true;
        }
        return false;
    }



    /**
     * If the given event was cancelled successfully, it will remove all attendees and the
     * speaker from their respective lists of events
     * @param attending The list of the names of the attendees attending the event
     * @param canceledEvent The event getting canceled
     * @param speaker The names of the speaker of the event
     */
    public void cancelWholeEvent(List<String> attending, String canceledEvent, List<String> speaker, double price){

        for (String name : attending) {
            cancelMyEvent(name, canceledEvent, price);
        }
        for (String name: speaker){cancelMyEvent(name, canceledEvent, price);}

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
            return pass.equals(getOrganizer(name).getPassword());
        }
        if (type.equals("speaker") && getSpeakers().containsKey(name)){
            return pass.equals(getSpeaker(name).getPassword());
        }
        if (type.equals("VIP") && getAllVIPs().containsKey(name)){
            return pass.equals(getVIP(name).getPassword());
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

    public List<User> getListOfUsers(List<String> names){
        List<User> users = new ArrayList<>();
        for (String name: names){
            users.add(getUser(name));
        }
        return users;
    }

    public void changeCovid(String name, boolean positive){
        getUser(name).setHasCovid(positive);
    }

    /**
     * Checks if the user has enough money to attend the event
     * @param name The name of the user
     * @param price The price of the event
     */
    public boolean canAfford(String name, double price) {
        if (price <= getUser(name).getBalance()) {
            return true;
        }
        else
            return false;
    }

    /**
     * Returns a list of the given user's groupchats
     * @param name The person we are looking at
     * @return The list of group chats the person is in
     */
    public List<String> getMyGroupChats(String name){
        return getUser(name).getGroupChats();
    }
}

