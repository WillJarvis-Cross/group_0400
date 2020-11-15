package Controllers;
import UseCases.*;
import Presenter.*;

import java.time.LocalDateTime;
import Entities.Event;
import java.util.Set;
import java.util.ArrayList;

/** Represents the controller for organiser manager object
 * @author group 400
 */
public class SpeakerController implements UserController{

    private EventManager events;
    private UserManager usermanager;
    private MessageManager mManager;
    private String name;


    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer
     * @param password password of Organizer
     * @param usermanager UserManager of the program
     * @param events EventManager fo the program
     */
    public SpeakerController(String name, String password, UserManager usermanager, EventManager events, MessageManager messageManager){
        this.events = events;
        this.usermanager = usermanager;
        this.mManager = messageManager;
        if(usermanager.login(name, password)){
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
            this.mManager.sendMessage(usermanager.getOrganizer(name), usermanager.getAttendee(attendeeName), content);
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
                this.mManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getAttendee(key), content);
            }
        }
    }

    /**
     * Uses the presenter to show the main menu for the speaker and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = presenter.printSpeaker();
            if (input.equals("1")){
                this.mManager.sendMessage(name);
                break;
            }
            else if (input.equals("2")){
                this.mManager.printMyMessages(name);
                break;
            }
            else if (input.equals("3")){
                presenter.printSpeakerEvents(getMyEvents(usermanager.get));
            }
            else if (input.equals("4")){
                this.mManager.messageAllAttendees(name);
                break;
            }
            else if (input.equals("5")){
                //TODO sign out
            }
            else{
                presenter.printInvalidInput();
            }
        }
    }
}