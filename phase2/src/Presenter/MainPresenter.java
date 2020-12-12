package Presenter;

import java.util.Scanner;


/** Represents the Presenter for the UI, specifically for MessageController
 * @author group 400
 */
public class MainPresenter {
    private Scanner sc = new Scanner(System.in);

    /**
     * Asks the user what type of user they are and returns their answer
     * @return what type of user this user is
     */
    public String printAttendeeOrOrganizer() {
        System.out.println("Are you an attendee, organizer, VIP, or a speaker?");
        System.out.println("1: Attendee");
        System.out.println("2: Organizer");
        System.out.println("3: Speaker");
        System.out.println("4: VIP");
        return sc.nextLine();
    }

    /**
     * Asks the user if they want to load saved files and returns their answer
     * @return whether or not the user wants to load the saved files
     */
    public boolean loadFromSave(){
        while (true){
            System.out.println("Do you want to load saved files? (Only load if you have previously saved)");
            System.out.println("1: Yes");
            System.out.println("2: No");
            String input = sc.nextLine();
            if (input.equals("1")){
                return true;
            }
            else if (input.equals("2")){
                return false;
            }
            else{
                System.out.println("Incorrect input, please try again");
            }
        }
    }

    /**
     * Asks the user if they want to save their progress and returns their answer
     * @return whether or not the user wants to save their progress
     */
    public boolean saveWhenExit(){
        while (true){
            System.out.println("Do you want to save the changes made this session?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            String input = sc.nextLine();
            if (input.equals("1")){
                return true;
            }
            else if (input.equals("2")){
                return false;
            }
            System.out.println("Incorrect input, please try again");
        }
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
     * prints that the user's input was invalid
     *
     */
    public void printInvalid() {
        System.out.println("That input was invalid");
    }
}
