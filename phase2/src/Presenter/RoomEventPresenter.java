package Presenter;

import Entities.Event;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for RoomController and EventController
 * @author group 400
 */
public class RoomEventPresenter extends Presenter{
    private Scanner sc = new Scanner(System.in);
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
        System.out.println("Enter the time of the new event in the form yyyy/mm/dd/hh (in military time)");
        String thisTime = sc.nextLine();
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
            System.out.println("What is the capacity of the room?");
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Prints that the room was successfully added
     *
     */
    public void printRoomAdded(){
        System.out.println("Room added successfully");
    }

    /**
     * Prints all the events
     *
     * @param events The list of events being printed
     */
    public void printAllEvents(List<Event> events){
        if (events.isEmpty()) {
            System.out.println("There are no events");
        }
        else {
            System.out.println("Here are all the events");
            for (Event name:events){
                System.out.println(name.getEventName());
            }
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
     * Prompts the user to enter the event they want to change capacity
     *
     * @return User input
     */
    public String printChangeEventCapacity(){
        System.out.println("Enter which event's capacity to change or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    /**
     * Prompts the user to enter the capacity of an event
     *
     * @return User input
     */
    public int printEventCapacity(){
        System.out.println("What is the capacity of the event?");
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Prints that the event was removed successfully
     *
     */
    public void printEventRemoved(){
        System.out.println("Event removed successfully");
    }

}
