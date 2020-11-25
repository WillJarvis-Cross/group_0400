package Presenter;

import java.util.List;
import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for MessageController
 * @author group 400
 */
public class MessagePresenter extends Presenter{
    private Scanner sc = new Scanner(System.in);

    /**
     * Asks speaker which event they want to send a message to
     * @return The event name
     */
    public String printMessageEvent(){
        System.out.println("Which event do you want to message");
        return sc.nextLine();
    }

    /**
     * Prints a message if the event they want to send a message to is empty.
     */
    public void printEmptyEvent(){
        System.out.println("There is no one at this event");
    }

    /**
     * Gets the name of a user that the person wants to send a message to
     *
     * @return User input
     */
    public String printWhoToSendTo() {
        System.out.println("Enter the name of who you want to send your message to or enter 0 " +
                           "to get back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prints if the user inputs a name that is not stored in user
     *
     */
    public void printNoName() {
        System.out.println("There is no user that has that name");
    }

    /**
     * Gets the message that the user wants to send
     *
     * @return User input
     */
    public String printMessage() {
        System.out.println("Enter the message you want to send");
        return sc.nextLine();
    }

    /**
     * Prints a list of messages that were received by the user
     *
     * @param messages All the users messages
     * @return User input
     */
    public String printReceivedMessages(StringBuilder messages) {
        if (messages.toString().equals("My Messages:")){
            System.out.println("You have no messages");
        }
        else {
            System.out.println(messages);
        }
        System.out.println("Enter any key to go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prints that the message the user inputs has been sent
     *
     */
    public void printMessageSent(){
        System.out.println("Message Sent!");
    }

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
}
