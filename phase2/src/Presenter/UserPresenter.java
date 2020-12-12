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
     * Creates instance of the ExportHTML class, and hands all events to it
     *
     * @return User input
     */
    public String exportEventsToHTML() {
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("0: Go back to the event menu");
        System.out.println("1: Export Events to HTML");

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
            System.out.println("Here is a list of events you are signed up for, " +
                    "(*) denotes liked events");
            for (String name: events) {
                System.out.println(name);
            }
        }
    }

    /**
     * Prints a list of events that the user is signed up for
     *
     * @param events The attendee's events
     * @param likedEvents The attendee's liked events
     */
    public void printAttendeeEvents(List<String> events, List<String> likedEvents) {
        if (events.isEmpty()) {
            System.out.println("You are not signed up for any events");
        }
        else {
            System.out.println(
                    "Here is a list of events you are signed up for, " +
                            "(*) denotes liked events"
            );
            for (String name: events) {
                if (likedEvents.contains(name)) {
                    name += " (*)";
                }
                System.out.println(name);
            }
        }
    }

    /**
     * Prints a list of events that the user is signed up for
     *
     * @param events The attendee's events
     * @param likedEvents The attendee's liked events
     */
    public String printAttendeeLikeOption(List<String> events, List<String> likedEvents) {
        printAttendeeEvents(events, likedEvents);
        return sc.nextLine();
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
     * Shows the user the status of the export action on the screen
     * @param isSuccessful true iff export was successful, otherwise false
     */
    public void printExportStatus(boolean isSuccessful) {
        if (isSuccessful) System.out.println("Successfully exported schedule.");
        else System.out.println("Something went wrong! Could not export schedule.");
    }

    /**
     * Prompts the user for account type that they want to add
     *
     * @return User input
     */
    public String getAccountType() {
        System.out.println("Select an account type by entering the corresponding numbers");
        System.out.println("1: Organizer");
        System.out.println("2: Speaker");
        System.out.println("3: Attendee");
        System.out.println("4: VIP");
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

    /**
     * Prints that the user could not afford to attend the event
     *
     */
    public void printCantAfford(){
        System.out.println("You do not have enough money to attend this event");
    }

    /**
     * Prints the current balance of the user
     *
     */
    public void printCurrentBalance(double balance){

        System.out.println("Your current balance is " + balance);
    }

    /**
     * Prompts the user to enter the amount they want to add
     *
     */
    public double printAddToBalance(){
        while (true){
            System.out.println("Enter the amount you want to add to the balance");
            Double choice;
            try {
                choice = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = null;
            }
            if (choice != null){
                return choice;
            }
            printInvalidOption();
        }
    }

    /**
     * Prints statistics for the program
     * @param numUsers number of users in the program
     * @param numAttend number pf attendees logged into program
     * @param  numSpeakers number of speakers that have logged into the program
     * @param numVIPs number of VIP's that have logged into the program
     * @param numOrganizers mumber of organizers that have logged into the program
     * @param numSignUps  numbers of events that users have signed up for
     * @param popEvents the top 5 most popular events in the program

     */

    public void printStatistics(int numUsers, int numAttend, int numSpeakers, int numVIPs, int numOrganizers,
                                int numSignUps, List<String> popEvents){
        System.out.println("There are "+ numUsers+" users that have logged in using the program.");
        System.out.println("There are "+ numAttend +" attendees that have logged in using the program.");
        System.out.println("There are "+ numSpeakers +" speakers that have logged in using the program.");
        System.out.println("There are "+ numVIPs +" VIPs that have logged in using the program.");
        System.out.println("There are "+ numOrganizers +" organizers that have logged in using the program.");
        System.out.println("The total number of events users have signed up for is "+numSignUps+".");
        System.out.println("The top 5 most popular events are "+popEvents);
        System.out.println("Press enter to return to the main menu");
    }

    /**
     * Informs user that there are currently no availible events.

     */

    public void printNoEvents(){
        System.out.println("There are no available events");
    }
}
