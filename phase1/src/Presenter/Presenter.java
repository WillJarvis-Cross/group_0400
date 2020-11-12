package Presenter;

import java.util.List;



public class Presenter {


    public void printLogin() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Login");
        System.out.println("2: Create an account");
    }

    // Can be used to ask for the users login or to create a login
    public void printUsername() {
        System.out.println("Please enter your username");
    }

    public void printPassword() {
        System.out.println("Please enter your password");
    }

    public void printAttendee() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Sign up for an event");
        System.out.println("2: Send a message");
        System.out.println("3: See list of received messages");
        System.out.println("4: Logout");
    }

    public void printOrganizer() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Send one message");
        System.out.println("2: Create an event");
        System.out.println("3: See list of received messages");
        System.out.println("4: Send a message to all speakers");
        System.out.println("5: Send a message to all Attendess");
        System.out.println("4: Logout");
    }

    public void printSpeaker() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Send a message");
        System.out.println("2: See list of received messages");
        System.out.println("3: See list of events that you are speaking at");
        System.out.println("4: Send a message to everyone signed up for an event");
        System.out.println("5: Logout");
    }

    // Prints a list of events that are not at capacity
    public void printEventList(List<String> events) {
        System.out.println("Here is a list of events that are available.");
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("0: Signup for none");
        int n = 1;
        for (String name: events) {
            System.out.println(n + ": " + name);
        }

    }

    public void printAttendeeEvents(List<String> events) {
        System.out.println("Here is a list of events you are signed up for");
        for (String name: events) {
            System.out.println(name);
        }
    }

    public void printSpeakerEvents(List<String> events) {
        System.out.println("Here is a list of events you are speaking at");
        for (String name: events) {
            System.out.println(name);
        }
    }

    public void printWhoToSendTo() {
        System.out.println("Enter the name of who you want to send your message to");
    }

    public void printWhichEvent() {
        System.out.println("Enter the name of the event that you want to send a message to");
    }
    public void printMessage() {
        System.out.println("Enter the message you want to send");
    }

    public static void printReceivedMessages(StringBuilder messages) {
        System.out.println(messages);
    }

}
