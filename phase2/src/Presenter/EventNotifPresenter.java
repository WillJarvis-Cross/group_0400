package Presenter;

import java.util.Scanner;

/** Represents the Presenter for the UI, specifically for messages involving event related actions
 * @author group 400
 */
public class EventNotifPresenter {
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints if the event selected is empty
     */
    public void printEmptyEvent(){ //eventNotifications
        System.out.println("There is no one at this event");
    }

    /**
     * Prints if the user inputs a event name that is not stored in user
     *
     */
    public void printNoEvent() { // eventNotifications
        System.out.println("There is no event that has that name");
    }

    /**
     * Prints that the event was successfully created
     *
     */
    public void printEventCreated(){ //eventNotifications
        System.out.println("Event created successfully");
    }

    /**
     * Prints that the user has been signed up for a particular event
     *
     */
    public void printSignedUp(String eventName){ //eventNotifications
        System.out.println("You have been signed up for "+ eventName);
    }

    /**
     * Prints that the user could not be signed up for a particular event
     *
     * @param eventName The event the user tried to sign up for
     */
    public void printNotSignedUp(String eventName){ //eventNotifications
        System.out.println("You could not be signed up for "+ eventName+", please try again");
    }

    /**
     * Prints that the user's spot in the event was removed
     *
     */
    public void printRemovedEvent() { //eventNotifications
        System.out.println("Successfully canceled your spot in the event");
    }

    /**
     * Prints that the user's spot in the event could not be removed
     *
     */
    public void printCantRemove(){ //eventNotifications
        System.out.println("Could not remove you from the event");
    }

    /**
     * Prints that the event was removed successfully
     *
     */
    public void printEventRemoved(){ //eventNotifications
        System.out.println("Event removed successfully");
    }
}
