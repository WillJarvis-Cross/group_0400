package Controllers;

import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.io.Serializable;

/** Represents the controller for Attendee object
 * @author group 400
 */
public class AttendeeController extends UserController implements Serializable {

    /**
     * Creates and initialize an Attendee controller object
     * @param name name of Attendee
     */
    public AttendeeController(String name){
        super(name);
    }

    /**
     * Creates an AttendeeController given the user's name and all the use cases
     * This constructor is used for when the user is loading saved files
     * @param name The name of the user
     * @param userManager The UserManager
     * @param eventManager The EventManager
     * @param messageManager The MessageManager
     * @param roomManager The RoomManager
     */
    public AttendeeController(String name, UserManager userManager, EventManager eventManager,
                              MessageManager messageManager, RoomManager roomManager){
        super(name, userManager, eventManager, messageManager, roomManager);
    }
    /**
     * Creates a new Attendee with the users name and the password they input
     * After, it redirects the user to the main menu
     */
    public void makeNewAccount(){
        String input = getPresenter().printLogin();
        if (input.equals("2")){
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getPresenter().printPassword();
                if (password.equals("0")){
                    makeNewAccount();
                }
                else{
                    getUsermanager().addAttendee(getMyName(), password);
                    mainMenu();
                }
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
                if (getUsermanager().login(getMyName(), password, "attendee")){
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
     * Uses the getPresenter() to show the main menu for the attendee and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printAttendee();
            if (input.equals("1")){ // Sign up for an event
                signUp();
                break;
            }
            else if (input.equals("2")){ // Cancel spot in event
                removeMyEvent();
                break;
            }
            else if (input.equals("3")){ // Send a message to someone
                getMessageController().sendMessage(getMyName());
                break;
            }
            else if (input.equals("4")){ // See list of received messages
                getMessageController().printMyMessages(getMyName());
                break;
            }
            else if (input.equals("5")){ // Show attendee's list of events
                getPresenter().printAttendeeEvents(getMyEvents());
                if (getMyEvents().size() > 0){
                    getEventController().specificInfo();
                }
                else{
                    mainMenu();
                }
                break;
            }
            else if (input.equals("6")){ // See their archived messages
                getMessageController().seeArchivedMessages(getMyName());
                break;
            }
            else if (input.equals("7")){ // save and log out
                break;
            }
            else{
                getPresenter().printInvalidInput();
            }
        }

    }
}

