package Controllers;

import Gateways.ExportHTML;
import UseCases.*;

import java.io.Serializable;

/** Represents the controller for organiser manager object
 * @author group 400
 */
public class SpeakerController extends UserController implements Serializable {

    private static int speakersLoggedIn = 0;

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
     * @param conferenceManager The ConferenceManager
     */
    public SpeakerController(String name, UserManager userManager, EventManager eventManager,
                             MessageManager messageManager, RoomManager roomManager, GroupChatManager groupChatManager, ConferenceManager conferenceManager){
        super(name, userManager, eventManager, messageManager, roomManager, groupChatManager,conferenceManager);
    }

    /**
     * Makes a new Speaker given their name and password
     */
    public void makeNewAccount(){
        String input = getMenuPresenter().printLogin();
        if (input.equals("2")){
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getMenuPresenter().printPassword();
                if (password.equals("0")){
                    makeNewAccount();
                }
                else{
                    getUsermanager().addSpeaker(getMyName(), password);
                    speakersLoggedIn += 1;
                    usersLoggedIn += 1;
                    covidQuestions();
                }
            }
            else{
                setMyName(getMenuPresenter().printInvalidUsername());
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
            String password = getMenuPresenter().printPassword();
            if (password.equals("0")){
                makeNewAccount();
                zero = true;
                break;
            }
            else{
                if (getUsermanager().login(getMyName(), password)){
                    speakersLoggedIn += 1;
                    usersLoggedIn += 1;
                    break;
                }
                else{
                    getPresenter().printInvalidOption();
                }
            }
        }
        if (!zero)
        {
            covidQuestions();
        }

    }

    /**
     * Returns the total number of times speakers have logged in
     * @return the total number of times speakers have logged in
     */
    public static int getSpeakersLoggedIn(){
        return speakersLoggedIn;
    }

    /**
     * Uses the getPresenter() to show the main menu for the speaker and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getMenuPresenter().printSpeaker();
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
                    getPresenter().printExportStatus(exportMyEvents());
                    System.out.println("Export Complete");
                } else if (!decision.equals("2")) { //go back
                    System.out.println("invalid selection, going back");
                }
            }else if (input.equals("10")){
                changePass();
            }
            else{
                getPresenter().printInvalidOption();
            }
        }
    }
}