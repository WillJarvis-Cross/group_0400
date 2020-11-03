package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;

public abstract class User{
    // List of messages sent to this user
    private List<Integer> messageInbox;

    // Holds a list of the user's friends whom the user can message
    private List<String> friendList;

    // List of events the attendee is signed up for
    private List<String> events;

    // The user's username and password which is used to log in
    private String password, username;


    public User(String passwordInput, String nameInput){
        this.username = nameInput;
        this.password = passwordInput;
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();
        this.events = new ArrayList();
    }

    // Returns the user's list of messages
    public List<Integer> getMessageInbox() {
        return messageInbox;
    }

    // Adding a new message to the user's list of messages
    public void addMessage(int newMessage){ messageInbox.add(newMessage);}

    // Returns the list of the user's friends
    public List<String> getFriendList() {
        return friendList;
    }

    // Adds a User to the list of friends
    public void addFriend(String friend){ friendList.add(friend);}

    //Returns the user's password
    public String getPassword(){ return password;}

    // Changes the user's password
    public void setPassword(String newPassword){ password = newPassword;}

    // Returns the user's username
    public String getUsername(){ return username;}

    // Changes the user's username
    public void setUsername(String newUsername){ username = newUsername;}

    // Returns a list of the events the user is signed up for
    public List<String> getEvents(){ return events;}

    // Adds the event to the list of the user's event in chronological order
    public void addEvent(String newEvent, int pos){
        events.add(pos, newEvent);
    }

    // Remove event from the events list
    public void removeEvent(Event removedEvent){ events.remove(removedEvent);}


    // Returns whether this user is an organizer or not
    abstract boolean isOrganizer();

    abstract boolean isSpeaker();

}
