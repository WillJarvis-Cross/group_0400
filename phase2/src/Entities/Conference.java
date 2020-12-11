package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** This is an entity for a Conference which contains the name the list of rooms in the conference
 * in the room
 * @author group 0400
 */
public class Conference implements Serializable {

    private List<String> room; // List of the names of rooms that are in this conference

    /**
     * Constructs a new Conference
     */
    public Conference() {
        this.room = new ArrayList<>();
    }

    /**
     * Returns the list of rooms in this conference
     * @return the list of rooms in this conference
     */
    public List<String> getRoom() {
        return room;
    }

    /**
     * sets the rooms in this conference to those in the parameter
     * @param room The new rooms for this conference
     */
    public void setRoom(List<String> room) {
        this.room = room;
    }

    /**
     * Adds room to the list of rooms in this conference
     * @param room The room being added
     * @return True if the room was added successfully and false otherwise
     */
    public boolean addRoom(String room){
        if(!this.room.contains(room)){
            this.room.add(room);
            return true;
        }
        else{
            return false;
        }
    }
}
