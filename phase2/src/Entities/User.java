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
    private List<Integer> archivedMessages; // List of the user's archived messages
    private List<String> events;  // List of events the attendee is signed up for
    private List<String> groupChats; // List of the names of the group chats this user is in
    private List<String> eventLiked; // List of event names liked by the user
    private String password, username;  // The user's username and password which is used to log in
    private boolean hasCovid = false; // If this is true then they are a risk for covid
    private double balance; // The money they have

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
        this.archivedMessages = new ArrayList<>();
        this.groupChats = new ArrayList<>();
        this.eventLiked = new ArrayList<>();
        this.balance = 0.0;
    }

    /**
     * Returns a list of the group chats this user is in
     * @return a list of the group chats this user is in
     */
    public List<String> getGroupChats(){ return groupChats;}

    /**
     * Returns a list of the events this user has liked
     * @return a list of the events this user has liked
     */
    public List<String> getLikedEvents() { return eventLiked; }

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
     * Returns a list of all the user's archived messages
     * @return a list of all the user's archived messages
     */
    public List<Integer> getArchivedMessages(){ return archivedMessages; }

    /**
     * Add an archived message to the list of archived messages
     * @param newMessage The new message being added
     */
    public void addArchivedMessage(int newMessage){ archivedMessages.add(newMessage);}

    /**
     * Deletes the given message if it exists
     * @param id The id of the message being deleted
     * @return True if the message has been deleted and false otherwise
     */
    public boolean deleteMessage(int id){
        if (messageInbox.contains(id)){
            messageInbox.remove(new Integer(id));
            return true;
        }
        return false;
    }

    /**
     * deletes a messages from the list of archived messages
     * @param id The message being deleted
     * @return True if the message is deleted and false otherwise
     */
    public boolean delArchivedMessage(int id){
        if (archivedMessages.contains(id)){
            archivedMessages.remove(new Integer(id));
            return true;
        }
        return false;
    }

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
     * Adds a group chat to this user's list of group chats
     * @param name The name of the group chat
     */
    public void addGroupChat(String name){ groupChats.add(name);}

    /**
     * Remove the given group chat from this user's list of group chats if possible
     * @param name The name of the group chat
     * @return True if the group chat was deleted and false otherwise
     */
    public boolean removeGroupChat(String name){
        if (groupChats.contains(name)){
            groupChats.remove(name);
            return true;
        }
        return false;
    }

    /**
     * Returns true if this user is a risk for COVID-19 and false otherwise
     * @return true if this user is a risk for COVID-19 and false otherwise
     */
    public boolean getHasCovid(){return hasCovid;}

    /**
     * Sets the user's value of hasCovid based on in the parameter
     * @param positive True when this user is a risk for COVID-19 and false otherwise
     */
    public void setHasCovid(boolean positive){hasCovid = positive;}

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

    /**
     * Returns True or false depending on whether or not this user is a VIP or not
     * @return True or false depending on whether or not this user is a VIP or not
     */
    public abstract boolean isVIP();

    /**
     * Returns this User's balance
     * @return this User's balance
     */
    public double getBalance(){ return balance;}

    /**
     * Adds amount to this User's balance
     * @param amount The amount to add
     */
    public void addToBalance(double amount){balance += amount;}

    /**
     * Removes amount to this User's balance
     * @param amount The amount to remove
     */
    public void removeFromBalance(double amount){balance -= amount;}
}
