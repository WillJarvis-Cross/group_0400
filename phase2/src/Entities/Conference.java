package Entities;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/** This is an entity for a Room which contains the name of the room, the room's capacity, and the events that are
 * in the room
 * @author group 0400
 */
public class Conference implements Serializable {
    private String conferenceName;
    private List<String> room;

    public Conference(String conferenceNumber) {
        this.conferenceName = conferenceNumber;
        this.room = new ArrayList<>();
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public List<String> getRoom() {
        return room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }

    public boolean addRoom(String room){
        if(!this.room.contains(room)){
            this.room.add(room);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean removeRoom(String room){
        if(this.room.contains(room)) {
            this.room.remove(room);
            return true;
        }
        else{
            return false;
        }
    }

}
