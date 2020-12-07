package Controllers;


import Gateways.ExportHTML;
import UseCases.*;

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
     * @param conferenceManager
     */
    public VIPController(String name, UserManager userManager, EventManager eventManager,
                         MessageManager messageManager, RoomManager roomManager, GroupChatManager groupChatManager, ConferenceManager conferenceManager){
        super(name, userManager, eventManager, messageManager, roomManager, groupChatManager, conferenceManager);
    }

    public void makeNewAccount(){
        String input = getPresenter().printLogin();
        if (input.equals("2")){ // making new account
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getPresenter().printPassword();
                if (password.equals("0")){
                    makeNewAccount();
                }
                else{
                    getUsermanager().addVIP(getMyName(), password);
                    covidQuestions();
                    mainMenu();
                }
            }
            else{
                setMyName(getPresenter().printInvalidUsername());
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
            covidQuestions();
            mainMenu();
        }

    }

    /**
     * Uses the presenter to show the event menu for the organizer and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printAttendee();
            if (input.equals("1")){
                messageMenu();
            }
            else if (input.equals("2")){
                eventMenu();
            }
            else if (input.equals("3")){ // save and log out
                break;
            }
            else{
                getPresenter().printInvalidInput();
            }
        }

    }

    public void eventMenu(){
        String input = getPresenter().printAttendeeEvent();
        if (input.equals("1")){ // Sign up for an event
            signUp();
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
                schedule.setEvents(getEventController().getListOfEvents());
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
        String input = getPresenter().printAttendeeMessage();
        if (input.equals("1")){ // Send a message to someone
            getMessageController().sendMessage(getMyName());
        }
        else if (input.equals("2")){ // See list of received messages
            getMessageController().printMyMessages(getMyName());
        }
        else if (input.equals("3")){ // See their archived messages
            getMessageController().seeArchivedMessages(getMyName());
        }
        else if (input.equals("4")){ // Create a group chat
            getGroupChatController().createGroupChat(getMyName());
        }
        else if (input.equals("5")){ // See their group chats
            getGroupChatController().showGroupChats(getMyName());
        }
    }
}
