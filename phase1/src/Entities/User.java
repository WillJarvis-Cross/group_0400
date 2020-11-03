package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.LocalDateTime;

public abstract class User{
    // List of messages sent to this user
    private List<Message> messageInbox;

    // Holds a list of the user's friends whom the user can message
    private List<User> friendList;

    // List of events the attendee is signed up for
    private List<Event> events;

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
    public List<Message> getMessageInbox() {
        return messageInbox;
    }

    // Adding a new message to the user's list of messages
    public void addMessage(Message newMessage){ messageInbox.add(newMessage);}

    // Returns the list of the user's friends
    public List<User> getFriendList() {
        return friendList;
    }

    // Adds a User to the list of friends
    public void addFriend(User friend){ friendList.add(friend);}

    //Returns the user's password
    public String getPassword(){ return password;}

    // Changes the user's password
    public void setPassword(String newPassword){ password = newPassword;}

    // Returns the user's username
    public String getUsername(){ return username;}

    // Changes the user's username
    public void setUsername(String newUsername){ username = newUsername;}

    // Returns a list of the events the user is signed up for
    public List<Event> getEvents(){ return events;}

    // Takes in an event to add to the list of events. If the event does not overlap with any other events, then
    // it adds the event to the array list in chronological order and returns true. Otherwise it returns false.
    public boolean addEvent(Event newEvent){
        LocalDateTime thisTime = newEvent.getTime();
        LocalDateTime timePlusDuration = thisTime.plusHours(newEvent.getDuration());
        int i = 0;
        // This while loop finds the position as which the new event will be added
        while (i < events.size()){
            if (events.get(i).getTime().compareTo(thisTime) >= 0){
                break;
            }
            i++;
        }

        if (i == 0){ // This is the case where the event is being added to the beginning of the list
            if (events.size() == 0){
                events.add(newEvent);
                return true;
            }
            // Checking to see that the event does not overlap with the event in front of it.
            // .compareTo() returns a positive integer when timePlusDuration is before the time of the event
            // So checking this makes sure the no duration of the event overlaps
            if (events.get(i).getTime().compareTo(timePlusDuration) >= 0){
                events.add(i, newEvent);
                return true;
            }
        }
        else if (i == events.size()){ // This is the case where the event is being added to the end of the list.
            Event eventBefore = events.get(i - 1);
            LocalDateTime timeWithDuration = eventBefore.getTime().plusHours(eventBefore.getDuration());

            // Checking to see that the event does not overlap with the event behind it.
            // .compareTo() returns a negative integer when thisTime is ahead of the end time of the event
            if (timeWithDuration.compareTo(thisTime) <= 0){
                events.add(newEvent);
                return true;
            }
        }
        else{
            Event eventBefore = events.get(i - 1);
            Event eventAfter = events.get(i);
            LocalDateTime timeWithDuration = eventBefore.getTime().plusHours(eventBefore.getDuration());

            // Checking to see that the event does not overlap with the event behind it or in front of it.
            if (timeWithDuration.compareTo(thisTime) <= 0 && eventAfter.getTime().compareTo(timePlusDuration) >= 0){
                events.add(i, newEvent);
                return true;
            }
        }
        return false;
    }

    // Remove event from the events list
    public void removeEvent(Event removedEvent){ events.remove(removedEvent);}


    // Returns whether this user is an organizer or not
    abstract boolean isOrganizer();

}
