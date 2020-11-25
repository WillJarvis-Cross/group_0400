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

    /**
     * Creates an OrganizerController given the user's name and all the use cases
     * This constructor is used for when the user is loading saved files
     * @param name The name of the user
     * @param userManager The UserManager
     * @param eventManager The EventManager
     * @param messageManager The MessageManager
     * @param roomManager The RoomManager
     */
    public OrganizerController(String name, UserManager userManager, EventManager eventManager,
                               MessageManager messageManager, RoomManager roomManager){
        super(name, userManager, eventManager, messageManager, roomManager);
    }

    /**
     * Makes a new Organizer using the user's name and the inputted password
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
                setMyName(getPresenter().printInvalidUsername());
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
     * Allows the organizer to login into their account
     */
    public void loginExistingAccount(){
        boolean zero = false;
        while (true){
            String password = getPresenter().printPassword();
            if (password.equals("0")){
                makeNewAccount();
                zero = true;
                break;
            }
            else{
                if (getUsermanager().login(getMyName(), password, "organizer")){
                    break;
                }
                else{
                    getPresenter().printInvalidInput();
                }
            }
        }
        if (!zero)
        {
            mainMenu();
        }

    }

    /**
     * Uses the presenter to show the main menu for the organizer and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printOrganizer();
            if (input.equals("1")){ // Go to the message menu
                messageMenu();
                break;
            }
            else if (input.equals("2")){ // Go to the event menu
                eventMenu();
                break;
            }
            else if (input.equals("3")){ // Create a room
                getRoomController().makeRoomRequest();
                break;
            }
            else if (input.equals("4")){ // Create a speaker
                createSpeaker();
                break;
            }
            else if (input.equals("5")){ // This is when the user wants to save and log out
                break;
            }
            else{
                getPresenter().printInvalidInput();
            }
        }

    }

    /**
     * Uses the presenter to show the message menu for the organizer and perform certain
     * actions based on the input
     */
    public void messageMenu(){
        String input = getPresenter().printMessageMenu();
        if (input.equals("1")){ // Send a single message to someone
            getMessageController().sendMessage(getMyName());
        }
        else if (input.equals("2")){ // Print the list of this user's received messages
            getMessageController().printMyMessages(getMyName());
        }
        else if (input.equals("3")){ // Send a message to all speakers
            getMessageController().messageAllSpeakers(getMyName());
        }
        else if (input.equals("4")){ // Send a message to all attendees
            getMessageController().messageAllAttendees(getMyName());
        }
        else if (input.equals("0")){ // Go back to the main menu
            mainMenu();
        }
        else{
            getPresenter().printInvalidOption();
            messageMenu();
        }
    }

    /**
     * Uses the presenter to show the event menu for the organizer and perform certain
     * actions based on the input
     */
    public void eventMenu(){
        String input = getPresenter().printEventMenu();
        if (input.equals("1")){ // Create an event
            getEventController().makeEventRequest();
        }
        else if (input.equals("2")){ // Remove an event
            getEventController().removeEvent();
        }
        else if (input.equals("3")){ // Change an event's capacity
            getEventController().changeEventCapacity();
        }
        else if (input.equals("4")){ // Sign up for an event
            signUp();
        }
        else if (input.equals("5")){ // Cancel spot in event
            removeMyEvent();
        }
        else if (input.equals("6")){ // Show my list of events
            getPresenter().printAttendeeEvents(getMyEvents());
            if (getMyEvents().size() > 0){
                getEventController().specificInfo();
            }
            else{
                mainMenu();
            }
        }
        else if (input.equals("0")){ // Go back to the main menu
            mainMenu();
        }
        else{
            getPresenter().printInvalidOption();
            eventMenu();
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
}