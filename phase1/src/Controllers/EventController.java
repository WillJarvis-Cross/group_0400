package Controllers;

import UseCases.EventManager;
import Entities.Event;


import java.util.ArrayList;
import java.lang.String;

import java.time.LocalDateTime;

public class EventController {

    private EventManager eManager;
    public EventController(){
        eManager = new EventManager();
    }

    //makes event upon request from UI or ReadFile. need to be expand when the UI is implemented
    public boolean makeEventRequest(LocalDateTime time, int duration, String speaker, int capacity, String eventName, String roomNumber){
        if (eManager.scheduleEvent(time,duration, speaker, capacity, eventName, roomNumber)){
            return true;
        }
        else{
            return false;
        }
    }
    //adds attendee when user request
    public boolean addAttendee(String eventName, String username){
        if (this.eManager.isAtCapacity(eventName)){
            return false; //full
        }else{
            return this.eManager.addPersonToEvent(eventName,username);
        }

    }

    //remove event
    public boolean removeEvent(String name){
        return this.eManager.removeEvent(name);
    }



    // output to read and write gateway
    public String writeFileRequest(){
        ArrayList<Event> eventList = this.eManager.getEvents();
        String outString = "";
        for (int i = 0; i < eventList.size() ;i++){
            outString += eventList.get(i).toString()+",";
        }
        return outString;
    }





}
