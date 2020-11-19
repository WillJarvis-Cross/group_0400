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
                getUsermanager().addSpeaker(getMyName(), password);
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
     * Uses the getPresenter() to show the main menu for the speaker and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printSpeaker();
            if (input.equals("1")){
                getMessageController().sendMessage(getMyName());
                break;
            }
            else if (input.equals("2")){
                getMessageController().printMyMessages(getMyName());
                break;
            }
            else if (input.equals("3")){
                getPresenter().printSpeakerEvents(getMyEvents());
                if (getMyEvents().size() > 0){
                    getEventController().specificInfo();
                }
                else{
                    mainMenu();
                }
                break;
            }
            else if (input.equals("4")){
                getMessageController().messageAllAttendees(getMyName());
                break;
            }
            else if (input.equals("5")){
                break;
            }
            else{
                getPresenter().printInvalidInput();
            }
        }
    }
}