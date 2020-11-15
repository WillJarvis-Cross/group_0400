package Controllers;
import Presenter.Presenter;
import UseCases.*;

import java.time.LocalDateTime;
import Entities.Event;
/** Represents the controller for organiser manager object
 * @author group 400
 */
public class OrganizerController implements UserController{

    //private EventManager events;
    private final UserManager usermanager;
    private final Presenter presenter;
    private final EventController eventController;
    private final MessageController messageController;
    private final EventManager eventManager;
    private final String name;


    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer

     */
    public OrganizerController(String name){
        presenter = new Presenter();
        usermanager = new UserManager();
        eventController = new EventController(this, presenter);
        eventManager = eventController.geteManager();
        messageController = new MessageController(usermanager, this, presenter);
        this.name = name;
        makeNewAccount();
        //if(usermanager.login(name, password) && usermanager.getOrganizers().contains(usermanager.getUser(name))){
          //  OrganizerController.name = name;
        //}
    }

    public void makeNewAccount(){
        String input = presenter.printLogin();
        if (input.equals("2")){
            if (usermanager.canAddPerson(name)){
                String password = presenter.printPassword();
                usermanager.addOrganizer(name, password);
                mainMenu();
            }
            else{
                presenter.printIvalidUsername();
                makeNewAccount();
            }
        }
        else if (input.equals("1")){
            loginExistingAccount();
        }
        else{
            presenter.printInvalidOption();
            makeNewAccount();
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

    /**
     * Uses the presenter to show the main menu for the organizer and perform certain
     * actions based on the input
     */
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
                messageController.printMyMessages(name);
                break;
            }
            else if (input.equals("4")){
                messageController.messageAllSpeakers(name);
                break;
            }
            else if (input.equals("5")){
                messageController.messageAllAttendees(name);
                break;
            }
            else if (input.equals("6")){
                // TODO create new room using RoomController
            }
            else if (input.equals("7")){
                createSpeaker();
                break;
            }
            else if (input.equals("8")){
                signUp();
                break;
            }
            else if (input.equals("9")){
                //TODO sign out
            }
            else{
                presenter.printInvalidInput();
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

     */
    /*public void addSpeaker(String passwordInput, String nameInput){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            usermanager.addSpeaker(nameInput, passwordInput);
        }
    }*/

    public void createSpeaker(){
        String speaker = presenter.printNameSpeaker();
        String pass = presenter.getPassSpeaker();
        if (usermanager.canAddPerson(speaker)){
            usermanager.addSpeaker(speaker, pass);
        }
        else{
            presenter.printIvalidUsername();
            createSpeaker();
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
            for(Event event: eventManager.getEvents()) {
                if (event.getTime().equals(time) && event.getRoomNum().equals(roomNumber)) {
                    e = event;
                }
            }
            if(e != null){
                eventManager.setSpeaker(e.getEventName(), speaker);
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
    /*public void messageAllAttendees(String content){
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
    }*/

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
    /*public void messageAllSpeakers(String content){
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
    }*/

    /**
     * signup for event selected in presenter
     *
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

}