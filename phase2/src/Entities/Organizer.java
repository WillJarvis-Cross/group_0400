package Entities;

import java.lang.String;

/** This is an entity for a User who is an organizer
 * @author group 0400
 */
public class Organizer extends User{

    /**
     * Constructs a new Organizer with the username nameInput and password passwordInput.
     * Calls the constructor of the superclass (User)
     * @param passwordInput   The organizer's password
     * @param nameInput   The organizer's username
     */
    public Organizer(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
    }

    /**
     * Returns whether or not the Organizer is an organizer or not
     * @return true
     */
    @Override
    public boolean isOrganizer() { return true;}

    /**
     * Returns whether or not the Organizer is a speaker or not
     * @return false
     */
    @Override
    public boolean isSpeaker() { return false;}

    /**
     * Returns whether or not the Attendee is a VIP or not
     * @return false
     */
    @Override
    public boolean isVIP() { return false;}
}
