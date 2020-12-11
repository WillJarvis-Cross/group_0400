package Controllers;

import Gateways.ExportHTML;
import UseCases.*;

import java.io.Serializable;

public class VIPController extends UserController implements Serializable {

    private static int vipsLoggedIn = 0;

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
     * @param conferenceManager The conference manager
     */
    public VIPController(String name, UserManager userManager, EventManager eventManager,
                         MessageManager messageManager, RoomManager roomManager, GroupChatManager groupChatManager, ConferenceManager conferenceManager){
        super(name, userManager, eventManager, messageManager, roomManager, groupChatManager, conferenceManager);
    }

    public void makeNewAccount(){
        String input = getMenuPresenter().printLogin();
        if (input.equals("2")){ // making new account
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getMenuPresenter().printPassword();
                if (password.equals("0")){
                    makeNewAccount();
                }
                else{
                    getUsermanager().addVIP(getMyName(), password);
                    vipsLoggedIn += 1;
                    usersLoggedIn += 1;
                    covidQuestions();
                }
            }
            else{
                setMyName(getMenuPresenter().printInvalidUsername());
                makeNewAccount();
            }
        }
        else if (input.equals("1")){ // login to existing account
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
            String password = getMenuPresenter().printPassword();
            if (password.equals("0")){
                makeNewAccount();
                zero = true;
                break;
            }
            else{
                if (getUsermanager().login(getMyName(), password)){
                    vipsLoggedIn += 1;
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
     * Returns the total number of times VIPs have logged in
     * @return the total number of times VIPs have logged in
     */
    public static int getVipsLoggedIn(){
        return vipsLoggedIn;
    }

    /**
     * Uses the presenter to show the event menu for the organizer and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        label:
        while (true){
            String input = getMenuPresenter().printAttendee();
            switch (input) {
                case "1":
                    messageMenu();
                    break;
                case "2":
                    eventMenu();
                    break;
                case "3":
                    addToBalance();
                    break;
                case "4":
                    changePass();
                    break;
                case "5":  // save and log out
                    break label;
                default:
                    getPresenter().printInvalidOption();
                    break;
            }
        }

    }

    public void eventMenu(){
        String input = getMenuPresenter().printAttendeeEvent();
        if (input.equals("1")){ // Sign up for an event
            if (getMyConference() == null){
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
            getPresenter().printAttendeeEvents(getMyEvents());
            if (getMyEvents().size() > 0){
                getEventController().specificInfo();
            }
        } else if (input.equals("4")) { //export to HTML
            String decision = getPresenter().exportEventsToHTML();

            if (decision.equals("1")){ //export
                ExportHTML schedule = new ExportHTML();
                getPresenter().printExportStatus(exportMyEvents());
                System.out.println("Export Complete");
            } else if (decision.equals("2")) { //go back
                eventMenu();
            } else {
                System.out.println("invalid selection, going back");
                eventMenu();
            }
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
