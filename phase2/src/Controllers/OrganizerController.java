package Controllers;

import Gateways.ExportHTML;
import UseCases.*;

import java.io.Serializable;

/** Represents the controller for organiser manager object
 * @author group 400
 */
public class OrganizerController extends UserController implements Serializable {

    private static int organizersLoggedIn = 0;

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
     * @param conferenceManager The Conference Manager
     */
    public OrganizerController(String name, UserManager userManager, EventManager eventManager,
                               MessageManager messageManager, RoomManager roomManager,
                               GroupChatManager groupChatManager,ConferenceManager conferenceManager){
        super(name, userManager, eventManager, messageManager, roomManager, groupChatManager, conferenceManager);
    }

    /**
     * Makes a new Organizer using the user's name and the inputted password
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
                    getUsermanager().addOrganizer(getMyName(), password);
                    organizersLoggedIn += 1;
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
                if (getUsermanager().login(getMyName(), password, "organizer")){
                    organizersLoggedIn += 1;
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
     * Returns the total number of times organizers have logged in
     * @return the total number of times organizers have logged in
     */
    public static int getOrganizersLoggedIn(){
        return organizersLoggedIn;
    }

    /**
     * Uses the presenter to show the main menu for the organizer and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        label:
        while (true){
            String input = getMenuPresenter().printOrganizer();
            switch (input) {
                case "1":  // Go to the message menu
                    messageMenu();
                    break;
                case "2":  // Go to the event menu
                    eventMenu();
                    break;
                case "3":  // Create a room
                    getConferenceController().makeConferenceRequest();
                    break;
                case "4":  // Create an account
                    createAccount();
                    break;
                case "5":  // Display statistics for a conference
                    displayStats();
                    break;
                case "6":  // This is when the user wants to save and log out
                    break label;
                default:
                    getPresenter().printInvalidOption();
                    break;
            }
        }

    }

    /**
     * Uses the presenter to show the message menu for the organizer and perform certain
     * actions based on the input
     */
    public void messageMenu(){
        String input = getMenuPresenter().printMessageMenu();
        switch (input) {
            case "1":  // Send a single message to someone
                getMessageController().sendMessage(getMyName());
                break;
            case "2":  // Print the list of this user's received messages
                getMessageController().printMyMessages(getMyName());
                break;
            case "3":  // Send a message to all speakers
                getMessageController().messageAllSpeakers(getMyName());
                break;
            case "4":  // Send a message to all attendees
                getMessageController().messageAllAttendees(getMyName());
                break;
            case "5":  // See their archived messages
                getMessageController().seeArchivedMessages(getMyName());
                break;
            case "6":  // Create a group chat
                getGroupChatController().createGroupChat(getMyName());
                break;
            case "7":  // See their group chats
                getGroupChatController().showGroupChats(getMyName());
                break;
            default:
                if (!input.equals("0")) {
                    getPresenter().printInvalidOption();
                    messageMenu();
                }
                break;
        }
    }

    /**
     * Uses the presenter to show the event menu for the organizer and perform certain
     * actions based on the input
     */
    public void eventMenu(){
        String input = getMenuPresenter().printEventMenu();
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
            if (getMyConference() == null){
                getPresenter().printNoEvents();
            }
            else{
                signUp();
            }
        }
        else if (input.equals("5")){ // Cancel spot in event
            removeMyEvent();
        }
        else if (input.equals("6")){ // Show my list of events
            getPresenter().printAttendeeEvents(getMyEvents());
            if (getMyEvents().size() > 0){
                getEventController().specificInfo();
            }
        } else if (input.equals("7")) { // Export events
            String decision = getPresenter().exportEventsToHTML();

            if (decision.equals("1")){ //export
                getPresenter().printExportStatus(exportMyEvents());
                System.out.println("Export Complete");

            } else if (decision.equals("2")) { //go back
                eventMenu();
            } else {
                System.out.println("invalid selection, going back");
                eventMenu();
            }
        } else {
            if (!input.equals("0")){
                getPresenter().printInvalidOption();
                eventMenu();
            }
        }
    }

    /**
     * Displays some statistics about the conference,then returns to the menu after user input.
     */
    public void displayStats(){
        getPresenter().printStatistics(UserController.getUsersLoggedIn(), AttendeeController.getAttendeesLoggedIn(),
                SpeakerController.getSpeakersLoggedIn(), VIPController.getVipsLoggedIn(),
                OrganizerController.getOrganizersLoggedIn(), UserController.getEventSignups(),
                getEventController().topFiveAttendedEvents());
        getMenuPresenter().returnMenu();
    }

    /**
     * Asks the user for the name and password for this new user and creates a new account with
     * that information
     */
    public void createAccount(){
        String input = getPresenter().getAccountType();
        String name = getMenuPresenter().printNameAccount();
        String pass = getMenuPresenter().getPassAccount();
        while (!getUsermanager().canAddPerson(name)) {
            name = getMenuPresenter().printInvalidUsername();
        }
        if (input.equals("1")) {
            getUsermanager().addOrganizer(name, pass);
        }
        else if (input.equals("2")) {
            getUsermanager().addSpeaker(name, pass);
        }
        else if (input.equals("3")) {
            getUsermanager().addAttendee(name, pass);
        }
        else if (input.equals("4")) {
            getUsermanager().addVIP(name, pass);
        }
        else {
            getPresenter().printInvalidOption();
        }

    }

}