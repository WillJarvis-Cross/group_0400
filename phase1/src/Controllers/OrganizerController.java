package Controllers;

import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.io.Serializable;

/** Represents the controller for organiser manager object
 * @author group 400
 */
public class OrganizerController extends UserController implements Serializable {

    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer

     */
    public OrganizerController(String name){
        super(name);
    }

    public OrganizerController(String name, UserManager userManager, EventManager eventManager, MessageManager messageManager, RoomManager roomManager){
        super(name, userManager, eventManager, messageManager, roomManager);
    }

    /**
     * Makes a new Organizer using the user's name and the inputed password
     */
    public void makeNewAccount(){
        String input = getPresenter().printLogin();
        if (input.equals("2")){
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getPresenter().printPassword();
                getUsermanager().addOrganizer(getMyName(), password);
                mainMenu();
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
                break;
            }
            else{
                getPresenter().printInvalidInput();
            }
        }

    }

    /**
     * Asks the user for the name and password for this new user and creates a new speaker with
     * that information
     */
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
    /*public void scheduleSpeaker(LocalDateTime time, String speaker, String roomNumber){
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
    }*/
}