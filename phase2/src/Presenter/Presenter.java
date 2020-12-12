package Presenter;

import java.util.Scanner;

/** Represents the Presenter for the UI
 * @author group 400
 */
public abstract class Presenter {
    private final Scanner sc = new Scanner(System.in);
    /**
     * Prints if the user inputs an invalid option
     *
     */
    public void printInvalidOption() {
        System.out.println("The option you selected was invalid, please try again");
    }

    /**
     * Prints that the message the user inputs has been sent
     *
     */
    public void printMessageSent(){
        System.out.println("Message Sent!");
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
     * try and catch for using Integer.parse
     * @param text The text we are parsing
     * @return the integer if Integer.parse was successful and false otherwise
     */
    public Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
