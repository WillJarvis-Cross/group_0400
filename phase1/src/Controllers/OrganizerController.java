package Controllers;
import UseCases.*;

import java.time.LocalDateTime;
import Entities.Event;
import java.util.Set;
/** Represents the controller for organiser manager object
 * @auther group 400
 */
public class OrganizerController{
    // are these public or private?
    EventManager events;
    UserManager usermanager;
    String name;


    /**
     * Creates and initialize an organzier controllor object
     * @param name
     * @param password
     * @param usermanager
     * @param events
     */
    public OrganizerController(String name, String password, UserManager usermanager, EventManager events){
        this.events = events;
        this.usermanager = usermanager;
        if(usermanager.login(name, password) && usermanager.getOrganizers().contains(usermanager.getUser(name))){
            this.name = name;
        }
    }

    /**
     * add event to organizer
     * <p>
     * first check if there is a username before allowing event to be created
     * to create event scheduleEvent is called
     * <p>
     * @param time
     * @param duration
     * @param speaker
     * @param capacity
     * @param eventName
     * @param roomNumber
     */
    public void addEvent(LocalDateTime time, int duration, String speaker,
                         int capacity, String eventName, String roomNumber){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            events.scheduleEvent(time, duration, speaker, capacity, eventName, roomNumber);
            //feel like it should have a check if the information enter is correct or not
            // scheduleEvent will return a bool depend on if the information is correct or not
            //also System output
        }
    }

    /**
     * create user as a speaker by giving username and password
     *
     * @param passwordInput
     * @param nameInput
     */
    public void addSpeaker(String passwordInput, String nameInput){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            usermanager.addSpeaker(nameInput, passwordInput);
        }
    }

    /**
     * assign speaker to events
     * <p>
     * first check if the user is logged in
     * second see if the event have a speaker
     * <p>
     * @param time
     * @param speaker
     * @param roomNumber
     */
    public void scheduleSpeaker(LocalDateTime time, String speaker, String roomNumber){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            Event e = null;
            for(Event event: events.getEvents()) {
                if (event.getTime().equals(time) && event.getRoomNum().equals(roomNumber)) {
                    e = event;
                }
            }
            if(e != null){
                events.setSpeaker(e.getEventName(), speaker);
            }
            else{ System.out.println("No event exists at that time and place. Use addEvent"); }
        }
    }

    /**
     * send message to a single attendee
     * <p>
     *     first check for if user is logged in
     *     next call message manager to send message
     * </p>
     *
     * @param attendeeName
     * @param content
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
     * sending all message to attendee
     *
     * @param content
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

    /**
     * direct message to the speaker
     *
     * @param speakerName
     * @param content
     */
    public void messageSpeaker(String speakerName, String content){
            if(name == null){
                System.out.println("Please log in.");
            }
            else {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getSpeaker(speakerName), content);
            }
    }

    /**
     * sending a message to speaker from organizer
     *
     * @param content
     */
    public void messageAllSpeakers(String content){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            Set<String> keys = usermanager.getSpeakers().keySet();
            for (String key : keys) {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getSpeaker(key), content);
            }
        }
    }

    /**
     * signup for event
     *
     * @param eventName
     */
    public void signUp(String eventName){

        usermanager.signUp(name,eventName);

    }

}