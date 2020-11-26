package Presenter;

import java.util.List;
import java.util.Scanner;


/** Represents the Presenter for the UI, specifically what will be accessed by UserController and its
 *  subclasses
 * @author group 400
 */
public class UserPresenter extends Presenter{
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the first text that the user will see
     *
     * @return User input
     */
    public String printLogin() {
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("1: Login");
        System.out.println("2: Create an account");
        return sc.nextLine();
    }

    /**
     * Asks the user for their password or to create a new one
     *
     * @return User input
     */
    public String printPassword() {
        System.out.println("Please enter your password or enter 0 to go back");
        return sc.nextLine();
    }

    /**
     * Prints an error statement if the username or password the user inputs is not valid
     *
     */
    public void printInvalidInput() {
        System.out.println("The input is incorrect, please try again");
    }

    /**
     * Prints options that Attendees can select
     *
     * @return User input
     */
    public String printAttendee() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Sign up for an event");
        System.out.println("2: Cancel spot in event");
        System.out.println("3: Send a message");
        System.out.println("4: See list of received messages");
        System.out.println("5: See list of events signed up for");
        System.out.println("6: Save and exit");
        return sc.nextLine();
    }

    public String printVIP() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Sign up for an event");
        System.out.println("2: Cancel spot in event");
        System.out.println("3: Send a message");
        System.out.println("4: See list of received messages");
        System.out.println("5: See list of events signed up for");
        System.out.println("6: Save and exit");
        return sc.nextLine();
    }

    /**
     * Prints options that Organizers can select
     *
     * @return User input
     */
    public String printOrganizer() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Access the message menu");
        System.out.println("2: Access the event menu");
        System.out.println("3: Create a new room");
        System.out.println("4: Create a new speaker");
        System.out.println("5: Save and exit");
        return sc.nextLine();
    }

    /**
     * Prints the options the user can do in the message menu and returns their action
     * @return The user's preferred action
     */
    public String printMessageMenu(){
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Send a message");
        System.out.println("2: Show my received messages");
        System.out.println("3: Send a message to all of the speakers");
        System.out.println("4: Send a message to all of the attendees");
        return sc.nextLine();
    }

    /**
     * Prints the options the user can do in the event menu and returns their action
     * @return The user's preferred action
     */
    public String printEventMenu(){
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Create an event");
        System.out.println("2: Delete an event");
        System.out.println("3: Change an event's capacity");
        System.out.println("4: Sign up for event");
        System.out.println("5: Cancel my spot in an event");
        System.out.println("6: Show the events I am signed up for");
        return sc.nextLine();
    }

    /**
     * Prints options that Speakers can select
     *
     * @return User input
     */
    public String printSpeaker() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Send a message");
        System.out.println("2: See list of received messages");
        System.out.println("3: See list of events that you are speaking at");
        System.out.println("4: Send a message to everyone signed up for an event");
        System.out.println("5: Save and exit");
        return sc.nextLine();
    }

    /**
     * Prints a list of events that the user is signed up for
     *
     * @param events The attendee's events
     */
    public void printAttendeeEvents(List<String> events) {
        if (events.isEmpty()) {
            System.out.println("You are not signed up for any events");
        }
        else {
            System.out.println("Here is a list of events you are signed up for");
            for (String name: events) {
                System.out.println(name);
            }
        }
    }

    /**
     * Prints a list of events that the speaker is speaking at
     *
     * @param events The speakers events
     */
    public void printSpeakerEvents(List<String> events) {
        if (events.isEmpty())
        {
            System.out.println("You have no events");
        }
        else{
            System.out.println("Here is a list of events you are speaking at");
            for (String name: events) {
                System.out.println(name);
            }
        }
    }

    /**
     * Prompts the user for the name of the speaker
     *
     * @return User input
     */
    public String printNameSpeaker(){
        System.out.println("Enter the name of the speaker");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the password of the speaker
     *
     * @return User input
     */
    public String getPassSpeaker(){
        System.out.println("Enter the speaker's password");
        return sc.nextLine();
    }

    /**
     * Lists the events the user can sign up for and asks which event they want to sign up for
     *
     * @param events The list of events to be printed
     * @return User input
     */
    public String getEventName(List<String> events){
        System.out.println("Enter the event you want to sign up for or enter 0 to go back to the main menu:");
        for (String event : events){
            System.out.println(event);
        }
        return sc.nextLine();
    }

    /**
     * Prints that the username the user inputs is invalid and asks for a different one
     * @return the user's input
     */
    public String printInvalidUsername(){
        System.out.println("That username is taken, please enter a different username");
        return sc.nextLine();
    }

    /**
     * Prints that the user has been signed up for a particular event
     *
     */
    public void printSignedUp(String eventName){
        System.out.println("You have been signed up for "+ eventName);
    }

    /**
     * Prints that the user could not be signed up for a particular event
     *
     * @param eventName The event the user tried to sign up for
     */
    public void printNotSignedUp(String eventName){
        System.out.println("You could not be signed up for "+ eventName+", please try again");
    }

    /**
     * Prompts the user to enter the event that they want to cancel
     *
     * @return User input
     */
    public String printDeleteEvent(){
        System.out.println("Enter which event you want to cancel");
        return sc.nextLine();
    }

    /**
     * Prints that the user's spot in the event was removed
     *
     */
    public void printRemovedEvent(){
        System.out.println("Successfully canceled your spot in the event");
    }

    /**
     * Prints that the user's spot in the event could not be removed
     *
     */
    public void printCantRemove(){
        System.out.println("Could not remove you from the event");
    }
}
