package Presenter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for menus and actions regarding events
 * @author group 400
 */
public class EventActionPresenter {
    private Scanner sc = new Scanner(System.in);
    /**
     * Prints the options the user can do in the event menu and returns their action
     * @return The user's preferred action
     */
    public String printEventMenu(){ //eventActions
        System.out.println("Select a an option by entering the corresponding numbers");
        System.out.println("0: Go back to the main menu");
        System.out.println("1: Create an event");
        System.out.println("2: Delete an event");
        System.out.println("3: Sign up for event");
        System.out.println("4: Cancel my spot in an event");
        System.out.println("5: Show the events I am signed up for");
        return sc.nextLine();
    }

    /**
     * Prints a list of events that are not at full capacity
     *
     * @param events All the events
     * @return User input
     */
    public String printEventList(List<String> events) { //eventActions
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
     * Prints a list of events that the user is signed up for
     *
     * @param events The attendee's events
     */
    public void printAttendeeEvents(List<String> events) { //eventActions
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

    /**
     * Asks speaker which event they want to send a message to
     * @return The event name
     */
    public String printMessageEvent(){ //eventActions
        System.out.println("Which event do you want to message");
        return sc.nextLine();
    }

    /**
     * Prints a list of events that the speaker is speaking at
     *
     * @param events The speakers events
     */
    public void printSpeakerEvents(List<String> events) { //eventActions
        if (events.isEmpty())
        {
            System.out.println("You have no events");
        }
        else{
            System.out.println("Here is a list of events you are speaking at");
            for (String name: events) {
                System.out.println(name);
            }
        }

    }

    /**
     * Prompts the user to enter the name of the new event
     *
     * @return User input
     */
    public String printNameOfEvent(){ //eventActions
        System.out.println("Enter the name of the new event or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the time of the event in the form yyyy/mm/dd/hh
     *
     * @return LocalDateTime of the User input
     */
    public LocalDateTime printTimeOfEvent(){ //eventActions
        System.out.println("Enter the time of the new event in the form yyyy/mm/dd/hh (in military time) or enter 0 " +
                "to go back to the main menu");
        String thisTime = sc.nextLine();
        if (thisTime.equals("0")){
            return null;
        }
        List<String> time = Arrays.asList(thisTime.split("/"));
        if (time.size() == 4){ // valid time
            int year = Integer.parseInt(time.get(0));

            int monthInt;
            int dayInt;
            int hourInt;

            String monthString = time.get(1);
            if (monthString.startsWith("0")){ // I want months entered as "4" instead of "04"
                monthInt = Integer.parseInt(String.valueOf(monthString.charAt(1)));
            }
            else{
                monthInt = Integer.parseInt(monthString);
            }

            String dayString = time.get(2);
            if (dayString.startsWith("0")){
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
                LocalDateTime finalTime =  LocalDateTime.of(year, monthInt, dayInt, hourInt, 0);
                if (finalTime.isAfter(LocalDateTime.now())){
                    return finalTime;
                }
            }
        }
        System.out.println("Invalid input, please try again");
        return printTimeOfEvent();
    }

    /**
     * Prompts the user to enter the duration of the event
     *
     * @return User input
     */
    public int printDurationOfEvent(){ //eventActions
        System.out.println("Enter the duration of the event in hours");
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Prompts the user to enter the speaker of the event
     *
     * @return User input
     */
    public String printSpeakerOfEvent(){ //eventActions
        System.out.println("Who do you want to be the speaker of the event?");
        return sc.nextLine();
    }

    /**
     * Lists the events the user can sign up for and asks which event they want to sign up for
     *
     * @param events The list of events to be printed
     * @return User input
     */
    public String getEventName(List<String> events){ //eventActions
        System.out.println("Enter the event you want to sign up for or enter 0 to go back to the main menu:");
        for (String event : events){
            System.out.println(event);
        }
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the event that they want to cancel
     *
     * @return User input
     */
    public String printDeleteEvent(){ //eventActions
        System.out.println("Enter which event you want to cancel");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter an event they want to know more about
     *
     * @return User input
     */
    public String printSpecificEvent(){ // eventActions
        System.out.println("Enter one of these events to see more specific information about them or press enter to" +
                " go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prints the event information of a specific event
     *
     * @param info The info being printed
     */
    public void printSpecificEventInfo(String info){ //eventActions
        System.out.println(info);
    }

    /**
     * Prints all the events
     *
     * @param events The list of events being printed
     */
    public void printAllEvents(List<Entities.Event> events){ //eventActions
        if (events.isEmpty()) {
            System.out.println("There are no events");
        }
        else {
            System.out.println("Here are all the events");
            for (Entities.Event name:events){
                System.out.println(name.getEventName());
            }
        }

    }

    /**
     * Prompts the user to enter the event they want to delete
     *
     * @return User input
     */
    public String printDeleteWholeEvent(){ //eventActions
        System.out.println("Enter which event to delete or enter 0 to go back to the main menu");
        return sc.nextLine();
    }
}
