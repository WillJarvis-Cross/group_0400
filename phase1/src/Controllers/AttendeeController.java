package Controllers;

/** Represents the controller for Attendee object
 * @author group 400
 */
public class AttendeeController extends UserController{

    /*private final UserManager usermanager;
    private final getPresenter() getPresenter();
    private final EventController eventController;
    private final getMessageController() getMessageController();
    private final EventManager eventManager;
    private final String name;*/

    /**
     * Creates and initialize an Attendee controller object
     * @param name name of Attendee
     */
    public AttendeeController(String name){
        super(name);
        /*getPresenter() = new getPresenter()();
        usermanager = new UserManager();
        eventController = new EventController(this, getPresenter());
        eventManager = eventController.geteManager();
        getMessageController() = new getMessageController()(usermanager, this, getPresenter());
        this.name = name;*/
        //makeNewAccount();
        //if(usermanager.login(name, password) && usermanager.getOrganizers().contains(usermanager.getUser(name))){
        //  OrganizerController.name = name;
        //}
    }

    public void makeNewAccount(){
        String input = getPresenter().printLogin();
        if (input.equals("2")){
            if (getUsermanager().canAddPerson(getMyName())){
                String password = getPresenter().printPassword();
                getUsermanager().addAttendee(getMyName(), password);
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
     * send message to a another attendee
     * <p>
     *     first check for if user is logged in
     *     next call message manager to send message
     * </p>
     *
     * @param recieverName name of receiver
     * @param content content of message
     */
    /*public void messageUser(String recieverName, String content, Boolean isAttendee){
        if (this.name == null) {
            System.out.println("Please log in.");
        }
        else {
            this.mManager.sendMessage(
                    usermanager.getAttendee(this.name),
                    isAttendee ? usermanager.getAttendee(recieverName) : usermanager.getSpeaker(recieverName),
                    content
            );
        }
    }*/
    /**
     * Retuns a list of events the user is attedning
     * @return list of events
     */
    /*public ArrayList<String> getMyEvents () {
        ArrayList<String> newList = new ArrayList<>();
        for (Event event : getEventManager().getEventsByUsername(getMyName())){
            newList.add(event.getEventName());
        }
        return newList;
    }*/

    /**
     * Adds attendee to the event with the passed event name. Returns true
     * if successful otherwise returns false
     *
     * @param eventName name of the event to signUp to
     * @return true if successfuly signed up to the event; otherwise false
     */
    /*public boolean attendEvent (String eventName) {
        if (this.events.canAddPerson(eventName)) {
            this.events.addPersonToEvent(eventName, this.name);
            return true;
        }
        return false;
    }/*


    /**
     * signup for event
     */
    /*public void signUp(){
        String eventName = getPresenter().getEventName();
        if (eventName.equals("0")){
            mainMenu();
        }
        else{
            if (usermanager.canSignUp(name, eventManager.getEvent(eventName), eventManager.getEventsByUsername(name))){
                usermanager.signUp(name,eventManager.getEvent(eventName),eventManager.getEventsByUsername(name));
                mainMenu();
            }
            else{
                getPresenter().printInvalidOption();
                signUp();
            }
        }

    }*/

    /**
     * Removes the attendee from an event with the passed event name.
     *
     * @param eventName name of the event to remove from
     */
    //public void cancelEvent (String eventName) { this.usermanager.cancelMyEvent(eventName); }

    /**
     * Uses the getPresenter() to show the main menu for the attendee and perform certain
     * actions based on the input
     */
    public void mainMenu(){
        while (true){
            String input = getPresenter().printAttendee();
            if (input.equals("1")){
                signUp();
                break;
            }
            else if (input.equals("2")){
                removeMyEvent();
                break;
            }
            else if (input.equals("3")){
                getMessageController().sendMessage(getMyName());
                break;
            }
            else if (input.equals("4")){
                getMessageController().printMyMessages(getMyName());
                break;
            }
            else if (input.equals("5")){
                getPresenter().printAttendeeEvents(getMyEvents());
                getEventController().specificInfo();
                break;
            }
            else if (input.equals("6")){
                //TODO sign out
            }
            else{
                getPresenter().printInvalidInput();
            }
        }

    }
}

