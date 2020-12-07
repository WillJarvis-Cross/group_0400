package Presenter;


import Entities.Event;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConferenceRoomPresenter extends Presenter{

    private Scanner sc = new Scanner(System.in);
    /**
     * Prompts the user to enter the name of the new event
     *
     * @return User input
     */
    public String printNameOfConference(){
        System.out.println("Enter the name of the new conference or enter 0 to go back to the main menu");
        return sc.nextLine();
    }

    public String printRoomAdded(){
        System.out.println("Enter the room name to add to the conference:");
        return sc.nextLine();
    }




    public void printConferenceAdded(){
        System.out.println("Conference added successfully");
    }
    public void printConferenceRemoved(){
        System.out.println("Conference remove successfully");
    }


}
