package Presenter;

import Entities.Event;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Represents the Presenter for the UI
 * @author group 400
 */
public class Presenter {

    //private Scanner sc;
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the first text that the user will see
     *
     * @return User input
     */
    public String printLogin() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Login");
        System.out.println("2: Create an account");
        return sc.nextLine();
    }

    public String printAttendeeOrOrganizer(){
        System.out.println("Are you an attendee, organizer, or a speaker?");
        System.out.println("1: Attendee");
        System.out.println("2: Organizer");
        System.out.println("3: Speaker");
        return sc.nextLine();
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
        System.out.println("2: Cancel spot in event");
        System.out.println("3: Send a message");
        System.out.println("4: See list of received messages");
        System.out.println("5: See list of events signed up for");
        System.out.println("6: Logout");
        return sc.nextLine();
    }

    /**
     * Prints options that Organizers can select
     *
     * @return User input
     */
    public String printOrganizer() {
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("1: Send one message");
        System.out.println("2: Create an event");
        System.out.println("3: Delete an event");
        System.out.println("4: See list of received messages");
        System.out.println("5: Send a message to all speakers");
        System.out.println("6: Send a message to all Attendees");
        System.out.println("7: Create a new room");
        System.out.println("8: Create a new speaker");
        System.out.println("9: Sign up for event");
        System.out.println("10: Cancel spot in event");
        System.out.println("11: See list of events signed up for");
        System.out.println("12: Logout");
        return sc.nextLine();
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
        return sc.nextLine();
    }

    /**
     * Prints a list of events that are not at full capacity
     *
     * @param events All the events
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
        return sc.nextLine();
    }

    /**
     * Prints if the user inputs an invalid option
     *
     */
    public void printInvalidOption() {
        System.out.println("The option you selected was invalid, please try again");
    }

    /**
     * Prints a list of events that the user is signed up for
     *
     * @param events The attendee's events
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
     * @param events The speakers events
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
     * Gets the name of an event an organizer wants to send a mass message to
     *
     * @return User input
     */
    public String printWhichEvent() {
        System.out.println("Enter the name of the event that you want to send a message to");
        return sc.nextLine();
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
        System.out.println(messages);
        if (messages.toString().equals("Messages:")){
            System.out.println("You have no messages");
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
     * Prompts the user to enter the name of the new event
     *
     * @return User input
     */
    public String printNameOfEvent(){
        System.out.println("Enter the name of the new event or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the time of the event in the form yyyy/mm/dd/hh
     *
     * @return LocalDateTime of the User input
     */
    public LocalDateTime printTimeOfEvent(){
        System.out.println("Enter the time of the new event in the form yyyy/mm/dd/hh (in military time) or enter 0 " +
                "to go back to the main menu");
        String thisTime = sc.nextLine();
        if (thisTime.equals("0")){
            return null;
        }
        List<String> time = Arrays.asList(thisTime.split("/"));
        if (time.size() == 4){
            int year = Integer.parseInt(time.get(0));

            int monthInt;
            int dayInt;
            int hourInt;

            String monthString = time.get(1);
            if (monthString.startsWith("0")){
                monthInt = Integer.parseInt(String.valueOf(monthString.charAt(1)));
            }
            else{
                monthInt = Integer.parseInt(monthString);
            }

            String dayString = time.get(2);
            if (monthString.startsWith("0")){
                dayInt = Integer.parseInt(String.valueOf(dayString.charAt(1)));
            }
            else{
                dayInt = Integer.parseInt(dayString);
            }

            String hourString = time.get(3);
            if (hourString.startsWith("0")){
                hourInt = Integer.parseInt(String.valueOf(hourString.charAt(1)));
            }
            else{
                hourInt = Integer.parseInt(hourString);
            }

            if (monthInt < 13 && dayInt < 32 && hourInt < 18 && hourInt > 8){
                return LocalDateTime.of(year, monthInt, dayInt, hourInt, 0);
            }
            return null;
        }
        System.out.println("Invalid input, please try again");
        return printTimeOfEvent();
    }

    /**
     * Prompts the user to enter the duration of the event
     *
     * @return User input
     */
    public int printDurationOfEvent(){
        System.out.println("Enter the duration of the event in hours");
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Prompts the user to enter the speaker of the event
     *
     * @return User input
     */
    public String printSpeakerOfEvent(){
        System.out.println("Who do you want to be the speaker of the event?");
        return sc.nextLine();
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
     * Prints that the event was successfully created
     *
     */
    public void printEventCreated(){
        System.out.println("Event created successfully");
    }

    /**
     * Prompts the user for the name of the speaker
     *
     * @return User input
     */
    public String printNameSpeaker(){
        System.out.println("Enter the name of the speaker");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the password of the speaker
     *
     * @return User input
     */
    public String getPassSpeaker(){
        System.out.println("Enter the speaker's password");
        return sc.nextLine();
    }

    /**
     * Prompts the user for the name of the speaker
     *
     * @return User input
     */
    public String getEventName(){
        System.out.println("Enter the event you want to sign up for or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prints that the username the user inputs is invalid
     *
     */
    public void printInvalidUsername(){
        System.out.println("That username is taken, please try again");
    }

    /**
     * Prints that the user has been signed up for a particular event
     *
     */
    public void printSignedUp(String eventName){
        System.out.println("You have been signed up for "+ eventName);
    }

    /**
     * Prints that the user could not be signed up for a particular event
     *
     * @param eventName The event the user tried to sign up for
     */
    public void printNotSignedUp(String eventName){
        System.out.println("You could not be signed up for "+ eventName+", please try again");
    }

    /**
     * Prompts the user to enter the event that they want to cancel
     *
     * @return User input
     */
    public String printDeleteEvent(){
        System.out.println("Enter which event you want to cancel");
        return sc.nextLine();
    }

    /**
     * Prints that the user's spot in the event was removed
     *
     */
    public void printRemovedEvent(){
        System.out.println("Successfully canceled your spot in the event");
    }

    /**
     * Prints that the user's spot in the event could not be removed
     *
     */
    public void printCantRemove(){
        System.out.println("Could not remove you from the event");
    }

    /**
     * Prompts the user to enter an event they want to know more about
     *
     * @return User input
     */
    public String printSpecificEvent(){
        System.out.println("Enter one of these events to see more specific information about them or press enter to" +
                " go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prints the event information of a specific event
     *
     * @param info The info being printed
     */
    public void printSpecificEventInfo(String info){
        System.out.println(info);
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
    public void printRoomAdded(){
        System.out.println("Room added successfullly");
    }

    /**
     * Prints all the events
     *
     * @param events The list of events being printed
     */
    public void printAllEvents(List<Event> events){
        System.out.println("Here are all the events");
        for (Event name:events){
            System.out.println(name.getEventName());
        }
    }

    /**
     * Prompts the user to enter the event they want to delete
     *
     * @return User input
     */
    public String printDeleteWholeEvent(){
        System.out.println("Enter which event to delete or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prints that the event was removed successfully
     *
     */
    public void printEventRemoved(){
        System.out.println("Event removed successfully");
    }
}
