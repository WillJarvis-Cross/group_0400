package Presenter;

import java.util.List;
import java.util.Scanner;

/** Represents the Presenter for the UI
 * @auther group 400
 */
public class Presenter {

    private static Scanner sc;
    Scanner sc = new Scanner(System.in);

    /**
     * Prints the first text that the user will see
     *
     * @return User input
     */
    public String printLogin() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Login");
        System.out.println("2: Create an account");
        String input = sc.nextLine();
        return input;
    }

    public String printAttendeeOrOrganizer(){
        System.out.println("Are you an attendee or a speaker?");
        System.out.println("1: Attendee");
        System.out.println("2: Organizer");
        return sc.nextLine();
    }

    /**
     * Asks the user for their username or to create a new one
     *
     * @return User input
     */
    public static String printUsername() {
        System.out.println("Please enter your username");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Asks the user for their password or to create a new one
     *
     * @return User input
     */
    public static String printPassword() {
        System.out.println("Please enter your password");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints an error statement if the username or password the user inputs is not valid
     *
     */
    public static void printInvalidInput() {
        System.out.println("The username or password is incorrect, please try again");
    }

    /**
     * Prints options that Attendees can select
     *
     * @return User input
     */
    public String printAttendee() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Sign up for an event");
        System.out.println("2: Send a message");
        System.out.println("3: See list of received messages");
        System.out.println("4: Logout");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints options that Organizers can select
     *
     * @return User input
     */
    public static String printOrganizer() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Send one message");
        System.out.println("2: Create an event");
        System.out.println("3: See list of received messages");
        System.out.println("4: Send a message to all speakers");
        System.out.println("5: Send a message to all Attendess");
        System.out.println("6: Logout");
        String input = sc.nextLine();
        return input;
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
        System.out.println("5: Logout");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints a list of events that are not at full capacity
     *
     * @param events
     * @return User input
     */
    public String printEventList(List<String> events) {
        System.out.println("Here is a list of events that are available.");
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("0: Signup for none");
        int n = 1;
        for (String name: events) {
            System.out.println(n + ": " + name);
        }
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints if the user inputs an invalid option
     *
     */
    public void printInvalidOption() {
        System.out.println("The option you selected was invalid");
    }

    /**
     * Prints a list of events that the user is signed up for
     *
     * @param events
     */
    public void printAttendeeEvents(List<String> events) {
        System.out.println("Here is a list of events you are signed up for");
        for (String name: events) {
            System.out.println(name);
        }
    }

    /**
     * Prints a list of events that the speaker is speaking at
     *
     * @param events
     * @return User input
     */
    public void printSpeakerEvents(List<String> events) {
        System.out.println("Here is a list of events you are speaking at");
        for (String name: events) {
            System.out.println(name);
        }
    }

    /**
     * Gets the name of a user that the person wants to send a message to
     *
     * @return User input
     */
    public static String printWhoToSendTo() {
        System.out.println("Enter the name of who you want to send your message to or enter 0 " +
                "to get back to the main menu");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints if the user inputs a name that is not stored in user
     *
     */
    public void printNoName() {
        System.out.println("There is no user that has that name");
    }

    /**
     * Gets the name of an event an organizer wants to send a mass message to
     *
     * @return User input
     */
    public String printWhichEvent() {
        System.out.println("Enter the name of the event that you want to send a message to");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints if the user inputs a event name that is not stored in user
     *
     */
    public void printNoEvent() {
        System.out.println("There is no event that has that name");
    }

    /**
     * Gets the message that the user wants to send
     *
     * @return User input
     */
    public static String printMessage() {
        System.out.println("Enter the message you want to send");
        String input = sc.nextLine();
        return input;
    }

    /**
     * Prints a list of messages that were received by the user
     *
     * @param messages
     * @return User input
     */
    public static String printReceivedMessages(StringBuilder messages) {
        System.out.println(messages);
        System.out.println("Enter any key to go back to the main menu");
        return sc.nextLine();
    }

    public static void printNoMessages(){
        System.out.println("You have no messages");
    }

    public static void printMessageSent(){
        System.out.println("Message Sent!");
    }

    public static String printNameOfEvent(){
        System.out.println("Enter the name of the new event or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    public static String printTimeOfEvent(){
        System.out.println("Enter the time of the new event");
        return sc.nextLine();
    }

}
