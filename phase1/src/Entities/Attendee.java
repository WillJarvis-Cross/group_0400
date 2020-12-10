package Entities;

import java.lang.String;

/** This is an entity for a User who attends events
 * @author group 0400
 */

public class Attendee extends User {

    /**
     * Constructs a new attendee with the username nameInput and password passwordInput.
     * Calls the constructor of the superclass (User)
     * @param passwordInput  The Attendee's password
     * @param nameInput   The Attendee's username
     */
    public Attendee(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
    }


    /**
     * Returns whether or not the Attendee is an organizer or not
     * @return false
     */
    @Override
    public boolean isOrganizer(){ return false;}

    /**
     * Returns whether or not the Attendee is a speaker or not
     * @return false
     */
    @Override
    public boolean isSpeaker() { return false;}
}
