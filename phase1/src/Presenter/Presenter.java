package Presenter;

import java.util.List;
import java.util.Scanner;



public class Presenter {

    Scanner sc = new Scanner(System.in);
    private String username;

    public String printLogin() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Login");
        System.out.println("2: Create an account");
        String input = sc.nextLine();
        return input;
    }

    // Can be used to ask for the users login or to create a login
    public String printUsername() {
        System.out.println("Please enter your username");
        String input = sc.nextLine();
        return input;
    }

    public String printPassword() {
        System.out.println("Please enter your password");
        String input = sc.nextLine();
        return input;
    }

    public String printAttendee() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Sign up for an event");
        System.out.println("2: Send a message");
        System.out.println("3: See list of received messages");
        System.out.println("4: Logout");
        String input = sc.nextLine();
        return input;
    }

    public String printOrganizer() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Send one message");
        System.out.println("2: Create an event");
        System.out.println("3: See list of received messages");
        System.out.println("4: Send a message to all speakers");
        System.out.println("5: Send a message to all Attendess");
        System.out.println("4: Logout");
        String input = sc.nextLine();
        return input;
    }

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

    // Prints a list of events that are not at capacity
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

    public String printAttendeeEvents(List<String> events) {
        System.out.println("Here is a list of events you are signed up for");
        for (String name: events) {
            System.out.println(name);
        }
        String input = sc.nextLine();
        return input;
    }

    public void printSpeakerEvents(List<String> events) {
        System.out.println("Here is a list of events you are speaking at");
        for (String name: events) {
            System.out.println(name);
        }
    }

    public String printWhoToSendTo() {
        System.out.println("Enter the name of who you want to send your message to");
        String input = sc.nextLine();
        return input;
    }

    public String printWhichEvent() {
        System.out.println("Enter the name of the event that you want to send a message to");
        String input = sc.nextLine();
        return input;
    }
    public String printMessage() {
        System.out.println("Enter the message you want to send");
        String input = sc.nextLine();
        return input;
    }

    public void printReceivedMessages() {
        // System.out.print(MessageController.printMyMessages(this.username));
    }

}
