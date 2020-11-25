package Presenter;

import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for room and message related options
 * @author group 400
 */
public class RoomMessagePresenter {
    private Scanner sc = new Scanner(System.in);

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

    /**
     * Prompts the user to enter the name of the room
     *
     * @return User input
     */
    public String printRoomNumber(){
        System.out.println("What will the room be called");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the room capacity of a room
     *
     * @return User input
     */
    public int printRoomCapacity(){
        System.out.println("What is the capacity of the room");
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Prints that the room was seccessfully added
     *
     */
    public void printRoomAdded() { //roomMessageOptions
        System.out.println("Room added successfullly");
    }

    /**
     * Gets the name of a user that the person wants to send a message to
     *
     * @return User input
     */
    public String printWhoToSendTo() { //roomMessageMenu
        System.out.println("Enter the name of who you want to send your message to or enter 0 " +
                "to get back to the main menu");
        return sc.nextLine();
    }

}
