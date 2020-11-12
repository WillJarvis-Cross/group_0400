package Controllers;
/** Represents the controller for EventManager
 * @auther group 400
 */
import UseCases.EventManager;
import Entities.Event;


import java.util.ArrayList;
import java.lang.String;

import java.time.LocalDateTime;

public class EventController {

    private EventManager eManager;

    /**
     * initialize a clean EventController with new EventManager
     */
    public EventController(){
        eManager = new EventManager();
    }

    /**
     * initialize a EventController with a already defined manager
     * @param manager
     */
    public EventController(EventManager manager){
        eManager = manager;
    }

    /**
     * create a event when a create event request is made
     * calls eventManger to check if event can be created with the information
     * @param time
     * @param duration
     * @param speaker
     * @param capacity
     * @param eventName
     * @param roomNumber
     * @return True if event is created, false if  event cannot be create with the invalid input
     */
    public boolean makeEventRequest(LocalDateTime time, int duration, String speaker, int capacity, String eventName, String roomNumber){
        if (eManager.scheduleEvent(time,duration, speaker, capacity, eventName, roomNumber)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * add attendee to event
     * First check if event still have space
     *
     * @param eventName
     * @param username
     * @return True if attendee is added and false if not added
     */
    public boolean addAttendee(String eventName, String username){
        if (this.eManager.isAtCapacity(eventName)){
            return false; //full
        }else{
            return this.eManager.addPersonToEvent(eventName,username);
        }

    }

    /**
     * remove Event from event list
     * @param name
     * @return true if event is deleted false if the event does not exist
     */
    public boolean removeEvent(String name){
        return this.eManager.removeEvent(name);
    }


    /**
     * when the program exist store all information needed to gateway
     *
     * @return a string of all event information to gateway to store
     */
    public String writeFileRequest(){
        ArrayList<Event> eventList = this.eManager.getEvents();
        String outString = "";
        for (int i = 0; i < eventList.size() ;i++){
            outString += eventList.get(i).toString()+",";
        }
        return outString;
    }





}
