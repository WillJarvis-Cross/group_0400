package Controllers;

import Gateways.ExportHTML;
import UseCases.*;

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
     * @param conferenceManager
     */
    public SpeakerController(String name, UserManager userManager, EventManager eventManager,
                             MessageManager messageManager, RoomManager roomManager, GroupChatManager groupChatManager, ConferenceManager conferenceManager){
        super(name, userManager, eventManager, messageManager, roomManager, groupChatManager,conferenceManager);
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
                    covidQuestions();
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
            covidQuestions();
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
            }
            else if (input.equals("2")){ // Show list of received messages
                getMessageController().printMyMessages(getMyName());
            }
            else if (input.equals("3")){ // Show events the speaker is speaking at
                getPresenter().printSpeakerEvents(getMyEvents());
                if (getMyEvents().size() > 0){
                    getEventController().specificInfo();
                }
            }
            else if (input.equals("4")){ // Send a message to everyone at a given event
                getMessageController().messageAllAttendeesAtEvent(getMyName());
            }
            else if (input.equals("5")){ // See the user's archived messages
                getMessageController().seeArchivedMessages(getMyName());
            }else if (input.equals("6")){ // Create a group chat
                getGroupChatController().createGroupChat(getMyName());
            }
            else if (input.equals("7")){ // See their group chats
                getGroupChatController().showGroupChats(getMyName());
            }
            else if (input.equals("8")){ // save and log out
                break;
            } else if (input.equals("9")) { //Export Events to HTML
                String decision = getPresenter().exportEventsToHTML();

                if (decision.equals("1")){ //export
                    ExportHTML schedule = new ExportHTML();
                    schedule.setEvents(getEventController().getListOfEvents());
                    System.out.println("Export Complete");
                } else if (decision.equals("2")) { //go back
                } else {
                    System.out.println("invalid selection, going back");
                }
            } else{
                getPresenter().printInvalidInput();
            }
        }
    }
}