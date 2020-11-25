package Presenter;

import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for the user related actions and options
 * @author group 400
 */
public class UserMenuPresenter {
    private Scanner sc = new Scanner(System.in);

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
     * Prints if the user inputs an invalid option
     *
     */
    public void printInvalidOption() {
        System.out.println("The option you selected was invalid, please try again");
    }

    /**
     * Prints if the user inputs a name that is not stored in user
     *
     */
    public void printNoName() {
        System.out.println("There is no user that has that name");
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
}
