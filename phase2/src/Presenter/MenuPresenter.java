package Presenter;

import java.util.Scanner;

public class MenuPresenter {
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
     * Prints options that Attendees can select
     *
     * @return User input
     */
    public String printAttendee() {
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("1: Enter the message menu");
        System.out.println("2: Enter the event menu");
        System.out.println("3: Add to your balance");
        System.out.println("4: Save and exit");
        return sc.nextLine();
    }

    public String printAttendeeEvent(){
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Sign up for an event");
        System.out.println("2: Cancel spot in event");
        System.out.println("3: See list of events signed up for");
        System.out.println("4: Export Events to HTML");
        return sc.nextLine();
    }

    public String printAttendeeMessage(){
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Send a message");
        System.out.println("2: See list of received messages");
        System.out.println("3: See your archived messages");
        System.out.println("4: Create a group chat");
        System.out.println("5: See your Group Chats");
        return sc.nextLine();
    }

    /**
     * Prints options that Organizers can select
     *
     * @return User input
     */
    public String printOrganizer() {
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("1: Access the message menu");
        System.out.println("2: Access the event menu");
        System.out.println("3: Access to conference");
        System.out.println("4: Create a new account");
        System.out.println("5: View Conference Statistics");
        System.out.println("6: Save and exit");
        return sc.nextLine();
    }

    /**
     * Prints the options the user can do in the message menu and returns their action
     * @return The user's preferred action
     */
    public String printMessageMenu(){
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Send a message");
        System.out.println("2: Show my received messages");
        System.out.println("3: Send a message to all of the speakers");
        System.out.println("4: Send a message to all of the attendees");
        System.out.println("5: See your archived messages");
        System.out.println("6: Create a group chat");
        System.out.println("7: See your Group Chats");
        return sc.nextLine();
    }

    /**
     * Prints the options the user can do in the event menu and returns their action
     * @return The user's preferred action
     */
    public String printEventMenu(){
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Create an event");
        System.out.println("2: Delete an event");
        System.out.println("3: Change an event's capacity");
        System.out.println("4: Sign up for event");
        System.out.println("5: Cancel my spot in an event");
        System.out.println("6: Show the events I am signed up for");
        System.out.println("7: Export Events to HTML");
        System.out.println("8: Add a speaker to an event");
        System.out.println(("9: Remove a speaker from an event"));
        return sc.nextLine();
    }

    public String addSpeakerToEvent(){
        System.out.println("Please write what event you would like to add the speaker to?");
        return sc.nextLine();
    }

    public String nameOfSpeaker(){
        System.out.println("What speaker would you like to add to the event?");
        return sc.nextLine();
    }

    /**
     * Prints options that Speakers can select
     *
     * @return User input
     */
    public String printSpeaker() {
        System.out.println("Select an option by entering the corresponding numbers");
        System.out.println("1: Send a message");
        System.out.println("2: See list of received messages");
        System.out.println("3: See list of events that you are speaking at");
        System.out.println("4: Send a message to everyone signed up for an event");
        System.out.println("5: See your archived messages");
        System.out.println("6: Create a group chat");
        System.out.println("7: See your Group Chats");
        System.out.println("8: Save and exit");
        System.out.println("9: Export Events to HTML");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the name of the account
     *
     * @return User input
     */
    public String printNameAccount(){
        System.out.println("Enter the Username of the Account");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the password of the accouont
     *
     * @return User input
     */
    public String getPassAccount(){
        System.out.println("Enter the Account's password");
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

    public boolean printCovidQuestions(){
        System.out.println("Answer 'yes' or 'no' to the following questions:");
        System.out.println("Have you come into contact with the virus or someone who has tested positive for the virus in the past 14 days?");
        if (sc.nextLine().equals("yes")){return true;}
        System.out.println("Have you been outside Ontario in the past month?");
        if (sc.nextLine().equals("yes")){return true;}
        System.out.println("Do you have any flu-like symptoms?");
        if (sc.nextLine().equals("yes")){return true;}
        return false;
    }

    public void printYouHaveCovid(){
        System.out.println("You can't sign up for any events because you are a risk for COVID-19");
    }

    /**
     * Takes in any user input and does nothing. (Detecting enter key)
     */
    public void returnMenu(){
        sc.nextLine();
    }

    public String speakerToBeRemoved(){
        System.out.println("Which speaker would you like to remove?");
        return sc.nextLine();
    }

    public String eventToBeRemoved(){
        System.out.println("What event do you want the speaker removed from?");
        return sc.nextLine();
    }
}
