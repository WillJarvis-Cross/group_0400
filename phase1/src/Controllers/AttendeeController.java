package Controllers;
import UseCases.*;

import java.time.LocalDateTime;
import Entities.Event;
import java.util.Set;
import java.util.ArrayList;
/** Represents the controller for Attendee object
 * @author group 400
 */
public class AttendeeController implements UserController{

    private EventManager events;
    private UserManager usermanager;
    private MessageManager mManager;
    private String name;

    /**
     * Creates and initialize an Attendee controller object
     * @param name name of Attendee
     * @param password password of Attendee
     * @param usermanager UserManager of the program
     * @param events EventManager of the program
     */
    public AttendeeController(String name, String password, UserManager usermanager, EventManager events,MessageManager messageManager) {
        this.events = events;
        this.usermanager = usermanager;
        this.mManager = messageManager;
        if (usermanager.login(name, password)) {
            this.name = name;
        }
    }

    /**
     * send message to a another attendee
     * <p>
     *     first check for if user is logged in
     *     next call message manager to send message
     * </p>
     *
     * @param recieverName name of receiver
     * @param content content of message
     */
    public void messageUser(String recieverName, String content, Boolean isAttendee){
        if (this.name == null) {
            System.out.println("Please log in.");
        }
        else {
            this.mManager.sendMessage(
                    usermanager.getAttendee(this.name),
                    isAttendee ? usermanager.getAttendee(recieverName) : usermanager.getSpeaker(recieverName),
                    content
            );
        }
    }
    /**
     * Retuns a list of events the user is attedning
     * @return list of events
     */
    public ArrayList<Event> getMyEvents () { return this.events.getEventsByUsername(this.name); }

    /**
     * Adds attendee to the event with the passed event name. Returns true
     * if successful otherwise returns false
     *
     * @param eventName name of the event to signUp to
     * @return true if successfuly signed up to the event; otherwise false
     */
    public boolean attendEvent (String eventName) {
        if (this.events.canAddPerson(eventName)) {
            this.events.addPersonToEvent(eventName, this.name);
            return true;
        }
        return false;
    }


    /**
     * signup for event
     */
    public void signUp(){
        String eventName = presenter.getEventName();
        if (eventName.equals("0")){
            mainMenu();
        }
        else{
            if (usermanager.canSignUp(name, eventManager.getEvent(eventName), eventManager.getEventsByUsername(name))){
                usermanager.signUp(name,eventManager.getEvent(eventName),eventManager.getEventsByUsername(name));
                mainMenu();
            }
            else{
                presenter.printInvalidOption();
                signUp();
            }
        }

    }

    /**
     * Removes the attendee from an event with the passed event name.
     *
     * @param eventName name of the event to remove from
     */
    public void cancelEvent (String eventName) { this.usermanager.cancelMyEvent(eventName); }

    /**
     * Uses the presenter to show the main menu for the attendee and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = presenter.printAttendee();
            if (input.equals("1")){
                signUp();
                break;
            }
            else if (input.equals("2")){
                messageController.sendMessage(name);
                break;
            }
            else if (input.equals("3")){
                messageController.printMyMessages(name);
                break;
            }
            else if (input.equals("4")){
                presenter.printAttendeeEvents(getMyEvents());
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

