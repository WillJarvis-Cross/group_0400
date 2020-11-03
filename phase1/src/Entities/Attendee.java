package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Attendee extends User{
    // List of the messages sent to the attendee
    private List<Message> messageInbox;

    // List of friends of the attendee
    private List<User> friendList;

    // List of events the attendee is signed up for
    private List<Event> events;

    public Attendee(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();
        this.events = new ArrayList();
    }

    // Returns a list of the events the user is signed up for
    public List<Event> getEvents(){ return events;}

    // Add event to list of events the user is attending
    public void addEvent(Event newEvent){ events.add(newEvent);}

    // Remove event from the events list
    public void removeEvent(Event removedEvent){ events.remove(removedEvent);}

    public boolean isOrganizer(){ return false;}
}
