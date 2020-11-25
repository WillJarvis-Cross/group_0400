package Entities;

import java.lang.String;

/** This is an entity for a User who is a speaker at events
 * @author group 0400
 */
public class Speaker extends User{

    /**
     * Constructs a new Speaker with the username nameInput and password passwordInput.
     * Calls the constructor of the superclass (User)
     * @param passwordInput   The speaker's password
     * @param nameInput   The speaker's username
     */
    public Speaker(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
    }

    /**
     * Returns whether or not the Speaker is an organizer or not
     * @return false
     */
    @Override
    public boolean isOrganizer() { return false;}

    /**
     * Returns whether or not the Speaker is a speaker or not
     * @return true
     */
    @Override
    public boolean isSpeaker() { return true;}
}
