package Controllers;

/** Represents the controller for Attendee object
 * @author group 400
 */
public class AttendeeController extends UserController{

    /**
     * Creates and initialize an Attendee controller object
     * @param name name of Attendee
     */
    public AttendeeController(String name){
        super(name);
    }

    /**
     * Creates a new Attendee with the users name and the password they input
     * After, it redirects the user to the main menu
     */
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
                System.out.println("Still have to do this");
                //TODO sign out
            }
            else{
                getPresenter().printInvalidInput();
            }
        }

    }
}

