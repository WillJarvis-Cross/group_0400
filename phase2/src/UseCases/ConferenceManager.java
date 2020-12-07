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

    public List<String> getConferences(){
        List<String> returnList = new ArrayList<>();
        Set<String> conferences = allConferenece.keySet();
        for (String conference: conferences){
            returnList.add(conference);
        }
        return returnList;
    }

    public boolean addConference(String conferenceNumber){
        if (allConferenece.containsKey(conferenceNumber)){
            return false;
        }
        allConferenece.put(conferenceNumber, new Conference(conferenceNumber));
        return true;
    }

    public boolean addRoomToConference(String conferenceNumber, String roomNumber){
        if (allConferenece.containsKey(conferenceNumber)){
            allConferenece.get(conferenceNumber).addRoom(roomNumber);
            return true;
        }else{
            return false;
        }

    }

    public boolean removeRoomFromConference(String conferenceNumber, String roomNumber){
        if(allConferenece.containsKey(conferenceNumber)){
            return allConferenece.get(roomNumber).removeRoom(roomNumber);
        }
        return false;

    }

    public boolean removeConference(String conferenceNumber){
        if(allConferenece.contains(conferenceNumber)){
            allConferenece.remove(conferenceNumber);
            return true;
        }
        return false;
    }

}
