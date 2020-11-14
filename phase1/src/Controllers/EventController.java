package Controllers;

import Entities.Room;
import UseCases.EventManager;
import Entities.Event;
import UseCases.RoomManager;
import UseCases.UserManager;


import java.util.ArrayList;
import java.lang.String;

import java.time.LocalDateTime;

/** Represents the controller for EventManager
 * @author group 400
 */
public class EventController {

    private EventManager eManager;
    private UserManager userManager;
    private RoomManager roomManager;

    /**
     * initialize a clean EventController with new EventManager, UserManager, and RoomManager
     */
    public EventController(){
        eManager = new EventManager();
        userManager = new UserManager();
        roomManager = new RoomManager();
    }

    /**
     * initialize a EventController with a already defined manager
     * @param manager The already defined EventManager
     */
    public EventController(EventManager manager){
        eManager = manager;
    }

    /**
     * create a event when a create event request is made
     * calls eventManager, userManager, and roomManager to check if event can be created with the information
     * @param time The time of the event
     * @param duration The duration of the event in hours
     * @param speaker The speaker of the event
     * @param eventName The name of the event
     * @param roomNumber The room the event is in
     * @return True if event is created, false if  event cannot be create with the invalid input
     */
    public boolean makeEventRequest(LocalDateTime time, int duration, String speaker, String eventName, String roomNumber){
        if (roomManager.isRoomTaken(roomNumber, time)){
            return false;
        }
        if (!eManager.canScheduleEvent(time, duration, speaker, eventName, roomNumber)){
            return false;
        }
        if (!userManager.canSignUp(speaker, eManager.getEvent(eventName), eManager.getEventsByUsername(speaker))){
            return false;
        }
        eManager.scheduleEvent(time, duration, speaker, eventName, roomNumber);
        userManager.signUp(speaker, eManager.getEvent(eventName), eManager.getEventsByUsername(speaker));
        roomManager.addEvent(roomNumber, eventName, time);
        return true;
    }

    /**
     * add attendee to event
     * First check if the event still has space and that the user can attend the event
     *
     * @param eventName The name of the event
     * @param username The name of the user
     * @return True if attendee is added and false if not added
     */
    public boolean addAttendee(String eventName, String username){
        Room thisRoom = roomManager.getRoom(eManager.getEvent(eventName).getRoomNum());
        if (thisRoom == null || this.eManager.isAtCapacity(eventName, thisRoom.getCapacity())){
            return false; //full
        }
        if (!eManager.canAddPerson(eventName)){
            return false;
        }
        if (!userManager.canSignUp(username, eManager.getEvent(eventName), eManager.getEventsByUsername(username))){
            return false;
        }
        eManager.addPersonToEvent(eventName, username);
        userManager.signUp(username, eManager.getEvent(eventName), eManager.getEventsByUsername(username));
        return true;
    }

    /**
     * remove Event from event list
     * @param name The name of the event
     * @return true if event is deleted false if the event does not exist
     */
    public boolean removeEvent(String name){
        if (!eManager.canRemoveEvent(name)){
            return false;
        }
        Event thisEvent = eManager.getEvent(name);
        userManager.cancelWholeEvent(thisEvent.getAttending(), name, eManager.getEvent(name).getSpeaker());
        eManager.removeEvent(name);
        roomManager.removeEvent(thisEvent.getRoomNum(), name);
        return true;
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
