package Controllers;
import UseCases.*;

import java.time.LocalDateTime;
import Entities.Event;
import java.util.Set;
/** Represents the controller for organiser manager object
 * @author group 400
 */
public class SpeakerController{

    private EventManager events;
    private UserManager usermanager;
    private String name;


    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer
     * @param password password of Organizer
     * @param usermanager UserManager of the program
     * @param events EventManager fo the program
     */
    public SpeakerController(String name, String password, UserManager usermanager, EventManager events){
        this.events = events;
        this.usermanager = usermanager;
        if(usermanager.login(name, password) && usermanager.getSpeaker(name)){
            this.name = name;
        }
    }

    /**
     * Retuns a list of events the speaker is attedning
     * @return list of events
     */
    public ArrayList<Event> getMyEvents () { return this.events.getEventsBySpeaker(this.name); }


    /**
     * send message to a single attendee
     * <p>
     *     first check for if user is logged in
     *     next call message manager to send message
     * </p>
     *
     * @param attendeeName name of receiver
     * @param content content of message
     */
    public void messageAttendee(String attendeeName, String content){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            MessageManager.sendMessage(usermanager.getOrganizer(name), usermanager.getAttendee(attendeeName), content);
        }
    }

    /**
     * sending message to all attendees
     *
     * @param content content of message
     */
    public void messageAllAttendees(String content){
        //Question shouldn't only send message to all attendee of the
        // event that the organizer host and not all attendee
        // For instance there can by more than 2 host are we sending all attendee a message??
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            Set<String> keys = usermanager.getAttendees().keySet();
            for (String key : keys) {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getAttendee(key), content);
            }
        }
    }

}