package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/** This is an entity for a User who is an organizer
 * @author group 0400
 */
public abstract class User implements Serializable {

    private List<Integer> messageInbox;  // List of messages sent to this user
    private List<String> events;  // List of events the attendee is signed up for
    private String password, username;  // The user's username and password which is used to log in

    /**
     * Constructs a User of types Attendee, Organizer, and Speaker. It instantiates the User's username and password,
     * as well as the messageInbox ArrayList and the events ArrayList.
     * @param passwordInput  The User's password
     * @param nameInput  The User's username
     */
    public User(String passwordInput, String nameInput){
        this.username = nameInput;
        this.password = passwordInput;
        this.messageInbox = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    /**
     * Returns the list of message IDs which correspond to this User's received messages
     * @return  The list of message IDs which correspond to this User's received messages
     */
    public List<Integer> getMessageInbox() {
        return messageInbox;
    }

    /**
     * Adds a given message ID to messageInbox
     * @param newMessage  The ID of the message that this User has received
     */
    public void addMessage(int newMessage){ messageInbox.add(newMessage);}

    /**
     * Returns this User's password
     * @return this User's password
     */
    public String getPassword(){ return password;}

    /**
     * Changes this User's old password to a password given by the input (newPassword)
     * @param newPassword This User's new password
     */
    public void setPassword(String newPassword){ password = newPassword;}

    /**
     * Returns this User's username
     * @return this User's username
     */
    public String getUsername(){ return username;}

    /**
     * Returns a list of this User's events by the names of the events
     * @return a list of this User's events by the names of the events
     */
    public List<String> getEvents(){ return events;}

    /**
     * Adds a given event name to events in the position inputted by the user
     * @param newEvent  The new Event which is added to events
     * @param pos  The position at which the event is being added
     */
    public void addEvent(String newEvent, int pos){
        events.add(pos, newEvent);
    }

    /**
     * Removes the inputted event name from events
     * @param removedEvent  The event getting removed
     */
    public void removeEvent(String removedEvent){
        events.remove(removedEvent);
    }

    /**
     * Returns True or false depending on whether or not this user is an organizer or not
     * @return True or false depending on whether or not this user is an organizer or not
     */
    public abstract boolean isOrganizer();

    /**
     * Returns True or false depending on whether or not this user is a speaker or not
     * @return True or false depending on whether or not this user is a speaker or not
     */
    public abstract boolean isSpeaker();

}
