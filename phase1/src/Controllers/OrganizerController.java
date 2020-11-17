package Controllers;

import java.time.LocalDateTime;
import Entities.Event;
/** Represents the controller for organiser manager object
 * @author group 400
 */
public class OrganizerController extends UserController{

    /*private final UserManager usermanager;
    private final Presenter presenter;
    private final EventController eventController;
    private final MessageController messageController;
    private final EventManager eventManager;
    private final String name;
*/

    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer

     */
    public OrganizerController(String name){
        super(name);
        /*presenter = new Presenter();
        usermanager = new UserManager();
        eventController = new EventController(this, presenter);
        eventManager = eventController.geteManager();
        messageController = new MessageController(usermanager, this, presenter);
        this.name = name;
        makeNewAccount();*/
        //if(usermanager.login(name, password) && usermanager.getOrganizers().contains(usermanager.getUser(name))){
          //  OrganizerController.name = name;
        //}
    }

    public void makeNewAccount(){
        String input = getPresenter().printLogin();
        if (input.equals("2")){
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getPresenter().printPassword();
                getUsermanager().addOrganizer(getMyName(), password);
                mainMenu();
                System.out.println("jik");
            }
            else{
                getPresenter().printInvalidUsername();
                makeNewAccount();
            }
        }
        else if (input.equals("1")){
            loginExistingAccount();
        }
        else{
            getPresenter().printInvalidOption();
            makeNewAccount();
        }
    }

    /*public void loginExistingAccount(){
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
    }*/

    /**
     * Uses the presenter to show the main menu for the organizer and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printOrganizer();
            if (input.equals("1")){
                getMessageController().sendMessage(getMyName());
                break;
            }
            else if (input.equals("2")){
                getEventController().makeEventRequest();
                break;
            }
            else if (input.equals("3")){
                getEventController().removeEvent();
                break;
            }
            else if (input.equals("4")){
                getMessageController().printMyMessages(getMyName());
                break;
            }
            else if (input.equals("5")){
                getMessageController().messageAllSpeakers(getMyName());
                break;
            }
            else if (input.equals("6")){
                getMessageController().messageAllAttendees(getMyName());
                break;
            }
            else if (input.equals("7")){
                getRoomController().makeRoomRequest();
                break;
            }
            else if (input.equals("8")){
                createSpeaker();
                break;
            }
            else if (input.equals("9")){
                signUp();
                break;
            }
            else if (input.equals("10")){
                removeMyEvent();
                break;
            }
            else if (input.equals("11")){
                getPresenter().printAttendeeEvents(getMyEvents());
                getEventController().specificInfo();
                break;
            }
            else if (input.equals("12")){
                System.out.println("Still have to do this");
                //TODO save
            }
            else{
                getPresenter().printInvalidInput();
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
        String speaker = getPresenter().printNameSpeaker();
        String pass = getPresenter().getPassSpeaker();
        if (getUsermanager().canAddPerson(speaker)){
            getUsermanager().addSpeaker(speaker, pass);
            mainMenu();
        }
        else{
            getPresenter().printInvalidUsername();
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
        if(getMyName() == null){
            System.out.println("Please log in.");
        }
        else{
            Event e = null;
            for(Event event: getEventManager().getEvents()) {
                if (event.getTime().equals(time) && event.getRoomNum().equals(roomNumber)) {
                    e = event;
                }
            }
            if(e != null){
                getEventManager().setSpeaker(e.getEventName(), speaker);
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
    /*public void signUp(){
        String eventName = getPresenter().getEventName();
        if (eventName.equals("0")){
            mainMenu();
        }
        else{
            if (getEventController().addAttendee(eventName, getMyName())){
                getPresenter().printSignedUp(eventName);
                mainMenu();
            }
            else{
                getPresenter().printNotSignedUp(eventName);
                signUp();
            }
        }
    }*/

}