package Controllers;

import Gateways.ExportHTML;
import UseCases.*;

import java.io.Serializable;

/** Represents the controller for Attendee object
 * @author group 400
 */
public class AttendeeController extends UserController implements Serializable {

    private static int attendeesLoggedIn = 0;

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
     * @param conferenceManager The Conference manager
     */
    public AttendeeController(String name, UserManager userManager, EventManager eventManager,
                              MessageManager messageManager, RoomManager roomManager, GroupChatManager groupChatManager, ConferenceManager conferenceManager){
        super(name, userManager, eventManager, messageManager, roomManager, groupChatManager,conferenceManager);
    }
    /**
     * Creates a new Attendee with the users name and the password they input
     * After, it redirects the user to the main menu
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
                    getUsermanager().addAttendee(getMyName(), password);
                    attendeesLoggedIn += 1;
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
                    attendeesLoggedIn += 1;
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
     * Returns the total number of times attendees have logged in
     * @return the total number of times attendees have logged in
     */
    public static int getAttendeesLoggedIn(){
        return attendeesLoggedIn;
    }

    /**
     * Uses the getPresenter() to show the main menu for the attendee and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        label:
        while (true){
            String input = getMenuPresenter().printAttendee();
            switch (input) {
                case "1": // Access message menu
                    messageMenu();
                    break;
                case "2": // Access event menu
                    eventMenu();
                    break;
                case "3": // add money to wallet
                    addToBalance();
                    break;
                case "4": // change password
                    changePass();
                    break;
                case "5":  // save and log out
                    break label;
                default: // invalid input
                    getPresenter().printInvalidOption();
                    break;
            }
        }

    }


    public void eventMenu(){
        String input = getMenuPresenter().printAttendeeEvent();
        if (input.equals("1")){ // Sign up for an event
            if (getMyConference() == null){ // The user is not in a conference
                getPresenter().printNoEvents();
            }
            else{
                signUp();
            }
        }
        else if (input.equals("2")){ // Cancel spot in event
            removeMyEvent();
        }
        else if (input.equals("3")){ // Show attendee's list of events
            getPresenter().printAttendeeEvents(getMyEvents(), getMyLikedEvents());
            if (getMyEvents().size() > 0){
                getEventController().specificInfo();
            }
        }
        else if (input.equals("4")) { //export to HTML
            String decision = getPresenter().exportEventsToHTML();

            if (decision.equals("0")) { //go back
                eventMenu();
            } else if (decision.equals("1")){ //export
                getPresenter().printExportStatus(exportMyEvents());
            } else {
                System.out.println("invalid selection, going back");
                eventMenu();
            }
        }
        else if (input.equals("5")) {
            likeUnlikeEvent();
        }
    }

    public void messageMenu(){
        String input = getMenuPresenter().printAttendeeMessage();
        switch (input) {
            case "1":  // Send a message to someone
                getMessageController().sendMessage(getMyName());
                break;
            case "2":  // See list of received messages
                getMessageController().printMyMessages(getMyName());
                break;
            case "3":  // See their archived messages
                getMessageController().seeArchivedMessages(getMyName());
                break;
            case "4":  // Create a group chat
                getGroupChatController().createGroupChat(getMyName());
                break;
            case "5":  // See their group chats
                getGroupChatController().showGroupChats(getMyName());
                break;
        }
    }
}

