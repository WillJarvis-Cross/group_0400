package Controllers;

import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.io.Serializable;

/** Represents the controller for organiser manager object
 * @author group 400
 */
public class SpeakerController extends UserController implements Serializable {

    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer
     */
    public SpeakerController(String name){
        super(name);
    }

    /**
     * Creates a SpeakerController given the user's name and all the use cases
     * This constructor is used for when the user is loading saved files
     * @param name The name of the user
     * @param userManager The UserManager
     * @param eventManager The EventManager
     * @param messageManager The MessageManager
     * @param roomManager The RoomManager
     */
    public SpeakerController(String name, UserManager userManager, EventManager eventManager,
                             MessageManager messageManager, RoomManager roomManager){
        super(name, userManager, eventManager, messageManager, roomManager);
    }

    /**
     * Makes a new Speaker given their name and password
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
                    getUsermanager().addSpeaker(getMyName(), password);
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
                if (getUsermanager().login(getMyName(), password, "speaker")){
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
     * Uses the getPresenter() to show the main menu for the speaker and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printSpeaker();
            if (input.equals("1")){ // Send a message to someone
                getMessageController().sendMessage(getMyName());
                break;
            }
            else if (input.equals("2")){ // Show list of received messages
                getMessageController().printMyMessages(getMyName());
                break;
            }
            else if (input.equals("3")){ // Show events the speaker is speaking at
                getPresenter().printSpeakerEvents(getMyEvents());
                if (getMyEvents().size() > 0){
                    getEventController().specificInfo();
                }
                else{
                    mainMenu();
                }
                break;
            }
            else if (input.equals("4")){ // Send a message to everyone at a given event
                getMessageController().messageAllAttendeesAtEvent(getMyName());
                break;
            }
            else if (input.equals("5")){ // See the user's archived messages
                getMessageController().seeArchivedMessages(getMyName());
                break;
            }else if (input.equals("6")){ // save and log out
                break;
            }
            else{
                getPresenter().printInvalidInput();
            }
        }
    }
}