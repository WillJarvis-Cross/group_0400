package Controllers;

import Entities.Room;
import Presenter.Presenter;
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
    private UserController userController;
    private Presenter presenter;

    /**
     * initialize a clean EventController with new EventManager, UserManager, and RoomManager
     */
    public EventController(UserController userController, Presenter presenter, EventManager eManager, UserManager userManager, RoomManager roomManager){
        this.eManager = eManager;
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.userController = userController;
        this.presenter = presenter;
    }

    /**
     * initialize a EventController with a already defined manager
     * @param manager The already defined EventManager
     */
    public EventController(EventManager manager){
        eManager = manager;
    }

    public EventManager geteManager() {
        return eManager;
    }

    /**
     * create a event when a create event request is made
     * calls eventManager, userManager, and roomManager to check if event can be created with the information
     * @return True if event is created, false if  event cannot be create with the invalid input
     */
    public void makeEventRequest(){
        String eventName = presenter.printNameOfEvent();
        if (eventName.equals("0")){
            userController.mainMenu();
        }
        LocalDateTime time = presenter.printTimeOfEvent();
        int duration = presenter.printDurationOfEvent();
        String speaker = presenter.printSpeakerOfEvent();
        String roomNumber = presenter.printRoomNumber();
        int counter = 0;
        if (!userManager.getSpeakers().containsKey(speaker) || roomManager.getRoom(roomNumber) == null){
            counter ++;
        }
        if (roomManager.isRoomTaken(roomNumber, time)){
            counter ++;
        }
        if (!eManager.canScheduleEvent(time, duration, speaker, eventName, roomNumber) || duration == 0){
            counter ++;
        }
        if (!userManager.getSpeakers().containsKey(speaker) || !userManager.canAddSpeaker(time, eManager.getEventsBySpeaker(speaker))){
            counter ++;
        }
        if (counter == 0){
            eManager.scheduleEvent(time, duration, speaker, eventName, roomNumber);
            userManager.signUp(speaker, eManager.getEvent(eventName), eManager.getEventsByUsername(speaker));
            roomManager.addEvent(roomNumber, eventName, time);
            presenter.printEventCreated();
            userController.mainMenu();
        }
        else{
            presenter.printInvalidOption();
            makeEventRequest();
        }
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

    public void specificInfo(){
        String newInput = presenter.printSpecificEvent();

        String info = eManager.getEventToString(newInput);
        if (info.equals("")){
            presenter.printInvalidOption();

        }
        else{
            presenter.printSpecificEventInfo(info);
        }
        userController.mainMenu();
    }

}
