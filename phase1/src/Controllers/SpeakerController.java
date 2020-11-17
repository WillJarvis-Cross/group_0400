package Controllers;

import Entities.Event;
import java.util.ArrayList;

/** Represents the controller for organiser manager object
 * @author group 400
 */
public class SpeakerController extends UserController{

    /*private EventManager events;
    private UserManager usermanager;
    private MessageManager mManager;
    private String name;*/


    /**
     * Creates and initialize an organizer controller object
     * @param name name of Organizer
     */
    public SpeakerController(String name){
        super(name);
        /*this.events = new EventManager();
        this.usermanager = new UserManager();
        this.mManager = messageManager;
        if(usermanager.login(name, password)){
            this.name = name;
        }*/
    }

    /**
     * Retuns a list of events the speaker is attedning
     * @return list of events
     */
    //public ArrayList<Event> getMyEvents () { return this.events.getEventsBySpeaker(this.name); }
    public ArrayList<String> getMyEvents () {
        ArrayList<String> newList = new ArrayList<>();
        for (Event event : getEventManager().getEventsBySpeaker(getMyName())){
            newList.add(event.getEventName());
        }
        return newList;
    }

    /**
     * send message to a single attendee
     * <p>
     *     first check for if user is logged in
     *     next call message manager to send message
     * </p>
     *
     * @param attendeeName name of receiver
     * @param content content of message
     */
    /*public void messageAttendee(String attendeeName, String content){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            this.mManager.sendMessage(usermanager.getOrganizer(name), usermanager.getAttendee(attendeeName), content);
        }
    }*/

    /**
     * sending message to all attendees
     *
     * @param content content of message
     */
    /*public void messageAllAttendees(String content){
        //Question shouldn't only send message to all attendee of the
        // event that the organizer host and not all attendee
        // For instance there can by more than 2 host are we sending all attendee a message??
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            Set<String> keys = usermanager.getAttendees().keySet();
            for (String key : keys) {
                this.mManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getAttendee(key), content);
            }
        }
    }*/

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
                getEventController().specificInfo();
                break;
            }
            else if (input.equals("4")){
                getMessageController().messageAllAttendees(getMyName());
                break;
            }
            else if (input.equals("5")){
                System.out.println("Still have to do this");
                //TODO sign out
            }
            else{
                getPresenter().printInvalidInput();
            }
        }
    }
}