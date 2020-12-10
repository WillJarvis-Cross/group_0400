package Presenter;

import Entities.Event;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
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
            Integer year = tryParse(time.get(0));

            Integer monthInt;
            Integer dayInt;
            Integer hourInt;

            String monthString = time.get(1);
            if (monthString.startsWith("0")){ // I want months entered as "4" instead of "04"
                monthInt = tryParse(String.valueOf(monthString.charAt(1)));
            }
            else{
                monthInt = tryParse(monthString);
            }

            String dayString = time.get(2);
            if (dayString.startsWith("0")){
                dayInt = tryParse(String.valueOf(dayString.charAt(1)));
            }
            else{
                dayInt = tryParse(dayString);
            }

            String hourString = time.get(3);
            if (hourString.startsWith("0")){
                hourInt = tryParse(String.valueOf(hourString.charAt(1)));
            }
            else{
                hourInt = tryParse(hourString);
            }
            if (year != null && monthInt != null && hourInt!=null && dayInt!=null && monthInt < 13 && dayInt < 32 &&
                    hourInt < 18 && hourInt > 8){
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
        while (true){
            System.out.println("Enter the duration of the event in hours");
            Integer choice = tryParse(sc.nextLine());
            if (choice != null) {
                return choice;
            }
            printInvalidOption();
        }
    }

    /**
     * Prompts the user to enter the speaker of the event
     *
     * @return User input
     */
    /** public String printSpeakerOfEvent(){
        System.out.println("Who do you want to be the speaker of the event?");
        return sc.nextLine();
    }
     **/

    public List<String> printSpeakerOfEvent(){
        List<String> speakers = new ArrayList<String>();
        Integer numberOfSpeakers;
        while (true){
            System.out.println("How many speakers would you like to add?");
            numberOfSpeakers = tryParse(sc.nextLine());
            if (numberOfSpeakers != null){
                break;
            }
            printInvalidOption();
        }

        for (int i = 0; i < numberOfSpeakers; i++) {
            System.out.println("Enter Speaker Name:");
            speakers.add(sc.nextLine());


        }
        return speakers;
    }


    /**
     * Prompts the user to enter the name of the room
     * @param newRoom if you are intending to create a new room then this is true
     * @return User input
     */
    public String printRoomNumber(boolean newRoom){
        if (newRoom){
            System.out.println("What will the room be called or enter 0 to go back");
        }
        else {
            System.out.println("Which room do you want to use");
        }
        return sc.nextLine();
    }

    public Boolean printVIP(){
        System.out.println("Will this event be VIP-only? (T/F)");
        String isVip = sc.nextLine();
        if (isVip.equals("T")) {
            return true;
        }else if (isVip.equals("F")) {
            return false;
        }else{
            return false;
        }
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
        while (true){
            System.out.println("What is the capacity of the room?");
            Integer choice = tryParse(sc.nextLine());
            if (choice != null){
                return choice;
            }
            printInvalidOption();
        }
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
        while (true){
            System.out.println("What is the capacity of the event?");
            Integer choice = tryParse(sc.nextLine());
            if (choice != null){
                return choice;
            }
            printInvalidOption();
        }

    }

    /**
     * Prints that the event was removed successfully
     *
     */
    public void printEventRemoved(){
        System.out.println("Event removed successfully");
    }

    /**
     * Prompts the user to enter the tech level of the room
     * @return User input
     */
    public int printRoomTech() {
        System.out.println("What is the tech level of the room? Please enter the number corresponding to the level:");
        return printTechLevels();
    }

    /**
     * Prompts the user to enter the tech level of the event
     * @return User input
     */
    public int printTechEvent() {
        System.out.println("What is the tech level of the event? Please enter the number corresponding to the level:");
        return printTechLevels();
    }

    /**
     * Prints the possible levels of technology to choose from
     * @return User input
     */
    public int printTechLevels() {
        while (true){
            System.out.println("1:Primitive");
            System.out.println("2:Basic");
            System.out.println("3:Regular");
            System.out.println("4:Advanced");
            System.out.println("5:Futuristic");
            Integer choice = tryParse(sc.nextLine());
            if (choice != null){
                return choice;
            }
            printInvalidOption();
        }
    }

    /**
     * Prints a list of rooms with a suitable tech level
     * @param roomsByTech The list of names of rooms with at least the required tech level
     */
    public void printRoomsSuitableTech(List<String> roomsByTech){
        System.out.println("The following rooms have the tech level of the event or greater:");
        for (String room: roomsByTech){
            System.out.println(room);
        }

    }

    /**
     * Prompts the user to enter the price of the event]
     * @return User input
     */
    public double printPrice() {
        while (true){
            System.out.println("Enter the price to attend the event");

            Integer choice = tryParse(sc.nextLine());
            if (choice != null){
                return choice;
            }
            printInvalidOption();
        }
    }

    public void printEventDoesnotExist(){
        System.out.println("Sorry the event you typed doesnot exist,please try adding again");
    }

    public void speakerNotFree(){
        System.out.println("The speaker is not free at this time, please choose another option");

    }

    public void speakerAdded(){
        System.out.println("Speaker was added succesfully!");
    }

    public void notASpeaker(){
        System.out.println("This user is not a speaker at this event!");
    }

    public void speakerRemoved(){
        System.out.println("This speaker was removed from the event");
    }

}
