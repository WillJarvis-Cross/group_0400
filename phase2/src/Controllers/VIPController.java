package Controllers;


import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.io.Serializable;

public class VIPController extends UserController implements Serializable {

    /**
     * Creates and initialize an organizer controller object
     *
     * @param name name of Organizer
     */
    public VIPController(String name) {
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
    public VIPController(String name, UserManager userManager, EventManager eventManager,
                               MessageManager messageManager, RoomManager roomManager){
        super(name, userManager, eventManager, messageManager, roomManager);
    }

    public void makeNewAccount(){
        String input = getPresenter().printLogin();
        if (input.equals("2")){
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getPresenter().printPassword();
                getUsermanager().addVIP(getMyName(), password);
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
                if (getUsermanager().login(getMyName(), password, "VIP")){
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
     * Uses the presenter to show the event menu for the organizer and perform certain
     * actions based on the input
     */
    public void mainMenu() {
        while (true) {
            String input = getPresenter().printVIP();
            if (input.equals("1")) { // Sign up for an event
                signUp();
                break;
            } else if (input.equals("2")) { // Cancel spot in event
                removeMyEvent();
                break;
            } else if (input.equals("3")) { // Send a message to someone
                getMessageController().sendMessage(getMyName());
                break;
            } else if (input.equals("4")) { // See list of received messages
                getMessageController().printMyMessages(getMyName());
                break;
            } else if (input.equals("5")) { // Show attendee's list of events
                getPresenter().printAttendeeEvents(getMyEvents());
                if (getMyEvents().size() > 0) {
                    getEventController().specificInfo();
                } else {
                    mainMenu();
                }
                break;
            } else if (input.equals("6")){
                getMessageController().seeArchivedMessages(getMyName());
                break;
            }
            else if (input.equals("7")) { // save and log out
                break;
            } else {
                getPresenter().printInvalidInput();
            }
        }
    }
}
