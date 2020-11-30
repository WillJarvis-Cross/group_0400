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
    public int printReceivedMessages(StringBuilder messages) {
        System.out.println(messages);
        System.out.println("Enter the number of the message you want to see more info about or enter 0" +
                " to go back to the main menu");
        return Integer.parseInt(sc.nextLine());
    }

    public void printNoMessages(){
        System.out.println("You have no messages");
    }

    public boolean printUnarchive(){
        while (true){
            System.out.println("Do you want to unarchive this message? Enter yes or no.");
            String input = sc.nextLine();
            if (input.equals("yes")){
                return true;
            }
            else if (input.equals("no")){
                return false;
            }
            printInvalidOption();
        }
    }

    /**
     * Prompts the user to select whether they want to "Mark as unread", delete, or archive their current message
     * @param sender The user who sent the message
     * @param content The content of the message
     * @return The users option
     */
    public int printMessageOption(String sender, String content, boolean unread){
        while (true){
            System.out.println("Pick an option for this message or enter 0 to go back");
            System.out.println(sender + ": " + content);
            if (unread){
                System.out.println("1: Mark as read");
            }
            else{
                System.out.println("1: Mark as unread");
            }
            System.out.println("2: delete");
            System.out.println("3: Archive");
            int input = Integer.parseInt(sc.nextLine());
            if (input >= 0 && input <=3){
                return input;
            }
            printInvalidOption();
        }
    }

    public void printDeleted(boolean del){
        if (del){
            System.out.println("Message successfully deleted");
        }
        else{
            System.out.println("Message could not be deleted");
        }
    }

    public void printArchived(boolean archived){
        if (archived){
            System.out.println("Message successfully archived");
        }
        else{
            System.out.println("Message could not be archived");
        }
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
