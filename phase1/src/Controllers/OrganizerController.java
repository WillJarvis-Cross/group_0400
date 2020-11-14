package Controllers;
import Presenter.Presenter;
import UseCases.*;

import java.time.LocalDateTime;
import Entities.Event;
import java.util.Set;
/** Represents the controller for organiser manager object
 * @author group 400
 */
public class OrganizerController{

    //private EventManager events;
    private final UserManager usermanager;
    private final Presenter presenter;
    private final EventController eventController;
    private final MessageController messageController;
    private final String name;


    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer
     * @param password password of Organizer
     * @param usermanager UserManager of the program
     * @param events EventManager fo the program
     */
    public OrganizerController(String name){
        presenter = new Presenter();
        eventController = new EventController();
        messageController = new MessageController();
        usermanager = new UserManager();
        this.name = name;
        makeNewAccount();
        //if(usermanager.login(name, password) && usermanager.getOrganizers().contains(usermanager.getUser(name))){
          //  OrganizerController.name = name;
        //}
    }

    public void makeNewAccount(){
        String input = presenter.printLogin();
        if (input.equals("2")){
            String password = presenter.printPassword();
            usermanager.addOrganizer(name, password);
            mainMenu();
        }
        else{
            loginExistingAccount();
        }
    }

    public void loginExistingAccount(){
        while (true){
            String password = presenter.printPassword();
            if (usermanager.login(name, password)){
                break;
            }
            else{
                presenter.printInvalidInput();
            }
        }
        mainMenu();
    }

    public void mainMenu(){
        while (true){
            String input = presenter.printOrganizer();
            if (input.equals("1")){
                messageController.sendMessage(name);
                break;
            }
            else if (input.equals("2")){
                eventController.makeEventRequest();
                break;
            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){

            }
            else if (input.equals("6")){

            }
            else{
                Presenter.printInvalidInput();
            }
        }

    }

    /**
     * add event to organizer
     * <p>
     * first check if there is a username before allowing event to be created
     * to create event scheduleEvent is called
     * <p>
     * @param time time of event
     * @param duration duration of event in hours
     * @param speaker name of speaker
     * @param eventName name of event
     * @param roomNumber room of event
     */
    /*public void addEvent(LocalDateTime time, int duration, String speaker,
                          String eventName, String roomNumber){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            events.scheduleEvent(time, duration, speaker, eventName, roomNumber);
            //feel like it should have a check if the information enter is correct or not
            // scheduleEvent will return a bool depend on if the information is correct or not
            //also System output
        }
    }*/


    /**
     * create user as a speaker by giving username and password
     *
     * @param passwordInput password of the new speaker
     * @param nameInput username of the new speaker
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
     * @param time time speaker is scheduled to
     * @param speaker name of speaker
     * @param roomNumber room speaker is scheduled to
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
     * @param attendeeName name of receiver
     * @param content content of message
     */
    /*public void messageSomeone(){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            MessageManager.sendMessage(usermanager.getOrganizer(name), usermanager.getAttendee(thisName), content);
        }

    }*/

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

    /**
     * direct message to the speaker
     *
     * @param speakerName name of receiver
     * @param content content of message
     */
    /*public void messageSpeaker(String speakerName, String content){
            if(name == null){
                System.out.println("Please log in.");
            }
            else {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getSpeaker(speakerName), content);
            }
    }*/

    /**
     * sending a message to speaker from organizer
     *
     * @param content content of message
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
     * @param eventName name of event
     */
    public void signUp(String eventName){

        usermanager.signUp(name,events.getEvent(eventName),events.getEventsByUsername(name));

    }

}