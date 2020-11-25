package Presenter;

import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for login/logout and loading options
 * @author group 400
 */
public class LoginLoadPresenter {
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
     * Asks the user what type of user they are and returns their answer
     * @return what type of user this user is
     */
    public String printAttendeeOrOrganizer(){
        System.out.println("Are you an attendee, organizer, or a speaker?");
        System.out.println("1: Attendee");
        System.out.println("2: Organizer");
        System.out.println("3: Speaker");
        return sc.nextLine();
    }

    /**
     * Asks the user if they want to load saved files and returns their answer
     * @return whether or not the user wants to load the saved files
     */
    public String loadFromSave(){
        System.out.println("Do you want to load saved files? (Only load if you have previously saved)");
        System.out.println("1: Yes");
        System.out.println("2: No");
        return sc.nextLine();
    }

    /**
     * Asks the user if they want to save their progress and returns their answer
     * @return whether or not the user wants to save their progress
     */
    public String saveWhenExit(){
        System.out.println("Do you want to save the changes made this session?");
        System.out.println("1: Yes");
        System.out.println("2: No");
        return sc.nextLine();
    }

    /**
     * Asks the user if they want to re-login after exiting
     * @return whether or not the user wants to log in again
     */
    public boolean relog() {
        System.out.println("Do you want to log in again or exit the program?");
        System.out.println("1: Log in Again");
        System.out.println("2: Exit");
        return (sc.nextLine().equals("1"));
    }

    /**
     * Asks the user for their username or to create a new one
     *
     * @return User input
     */
    public String printUsername() {
        System.out.println("Please enter your username");
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
     * Prints a message if the username is taken
     * @return user input
     */
    public String printInvalidUsername(){
        System.out.println("That username is taken, please enter a different username");
        return sc.nextLine();
    }
}
