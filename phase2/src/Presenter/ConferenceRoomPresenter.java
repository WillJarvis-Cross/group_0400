package Presenter;

import java.util.List;
import java.util.Scanner;

public class ConferenceRoomPresenter extends Presenter{

    private Scanner sc = new Scanner(System.in);
    /**
     * Prompts the user to enter the name of the new event
     *
     * @return User input
     */
    public String printNameOfConference(List<String> conferences){
        if (conferences != null){
            System.out.println("Here are the available conferences:");
            for (String conference: conferences){
                System.out.println(conference);
            }
            System.out.println("Enter the name of the conference you are joining");
        }
        else{
            System.out.println("Enter the name of the conference or enter 0 to go back to the main menu");
        }

        return sc.nextLine();
    }

    public String conferenceMain(){
        System.out.println("1. Make conference");
        System.out.println("2. Add room for conference");
        //System.out.println("3. remove conference");
        System.out.println("0. Go back");
        return sc.nextLine();
    }



    public void printConferenceAdded(){
        System.out.println("Conference added successfully");
    }
    public void printConferenceRemoved(){
        System.out.println("Conference remove successfully");
    }

    public void printNoConferences(){
        System.out.println("There are no conferences currently so you won't be able to sign up for any events");
    }
}
