package UseCases;
import Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class ConferenceManager implements Serializable {
    public Hashtable<String, Conference> allConferenece; // The conference numbers with the corresponding room

    /**
     * Creates a new conference manager
     */
    public ConferenceManager(){
        allConferenece = new Hashtable<>();
    }

    /**
     * Returns the conference associated with conferenceName
     * @param conferenceName The name of the conference
     * @return The conference object
     */
    public Conference getConference(String conferenceName){
        if (allConferenece.containsKey(conferenceName)){
            return allConferenece.get(conferenceName);
        }
        return null;
    }

    /**
     * Returns a list of all the conferences
     * @return a list of all the conferences
     */
    public List<String> getConferences(){
        List<String> returnList = new ArrayList<>();
        Set<String> conferences = allConferenece.keySet();
        for (String conference: conferences){
            returnList.add(conference);
        }
        return returnList;
    }

    /**
     * Creates a new conference
     * @param conferenceNumber The name of the conference
     * @return True if the conference was added and false otherwise
     */
    public boolean addConference(String conferenceNumber){
        if (allConferenece.containsKey(conferenceNumber)){
            return false;
        }
        allConferenece.put(conferenceNumber, new Conference());
        return true;
    }

    /**
     * Adds the given room to the given conference
     * @param conferenceNumber The name of the conference
     * @param roomNumber The name of the room
     * @return True if the room was added
     */
    public boolean addRoomToConference(String conferenceNumber, String roomNumber){
        if (allConferenece.containsKey(conferenceNumber)){
            allConferenece.get(conferenceNumber).addRoom(roomNumber);
            return true;
        }else{
            return false;
        }

    }

    /**
     * Returns true if conferenceNumber refers to a viable conference
     * @param conferenceNumber The name of the conference
     * @return true if conferenceNumber refers to a viable conference and false otherwise
     */
    public boolean checkConferenceExist(String conferenceNumber){
        return allConferenece.containsKey(conferenceNumber);
    }
}
