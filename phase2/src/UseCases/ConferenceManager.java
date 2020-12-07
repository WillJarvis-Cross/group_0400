package UseCases;
import Entities.*;
import UseCases.RoomManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class ConferenceManager implements Serializable {
    public Hashtable<String, Conference> allConferenece; // The conference numbers with the corresponding room

    public ConferenceManager(){
        allConferenece = new Hashtable<>();
    }
    public Conference getConference(String roomName){
        if (allConferenece.containsKey(roomName)){
            return allConferenece.get(roomName);
        }
        return null;
    }

    public boolean addConference(String conferenceNumber){
        if (allConferenece.containsKey(conferenceNumber)){
            return false;
        }
        allConferenece.put(conferenceNumber, new Conference(conferenceNumber));
        return true;
    }

    public void addRoomToConference(String conferenceNumber, String roomNumber){
        allConferenece.get(conferenceNumber).addRoom(roomNumber);
    }

    public void removeRoomFromConference(String conferenceNumber, String roomNumber){
        allConferenece.get(roomNumber).removeRoom(roomNumber);
    }

}
