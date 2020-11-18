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
     * initialize a clean EventController with given UserController,
     * EventManager, UserManager, Presenter, and RoomManager
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

    /**
     * create a event when a create event request is made
     * calls eventManager, userManager, and roomManager to check if event can be created with the information
     */
    public void makeEventRequest(){
        String eventName = presenter.printNameOfEvent();
        if (eventName.equals("0")){
            userController.mainMenu();
        }
        else{
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
                ArrayList<Event> speakerEvents = eManager.getEventsBySpeaker(speaker);
                speakerEvents.remove(eManager.getEvent(eventName));
                userManager.signUp(speaker, eManager.getEvent(eventName), speakerEvents);
                roomManager.addEvent(roomNumber, eventName, time);
                presenter.printEventCreated();
                userController.mainMenu();
            }
            else{
                presenter.printInvalidOption();
                makeEventRequest();
            }
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
        if (!eManager.containsEvent(eventName)){
            return false;
        }
        Room thisRoom = roomManager.getRoom(eManager.getEvent(eventName).getRoomNum());
        if (thisRoom == null || this.eManager.isAtCapacity(eventName, thisRoom.getCapacity())){
            return false; //full
        }
        if (!eManager.canAddPerson(eventName)){
            return false;
        }
        if (!userManager.canSignUp(eManager.getEvent(eventName).getTime(), eManager.getEventsByUsername(userManager.getUser(username)))){
            return false;
        }
        eManager.addPersonToEvent(eventName, username);
        ArrayList<Event> attendeeEvents = eManager.getEventsByUsername(userManager.getUser(username));
        attendeeEvents.remove(eManager.getEvent(eventName));
        userManager.signUp(username, eManager.getEvent(eventName), attendeeEvents);
        return true;
    }



    /**
     * remove a whole Event from event list and makes sure no users are still signed up for the event and it removes
     * the event from its room
     *
     */
    public void removeEvent(){
        presenter.printAllEvents(eManager.getEvents());
        String name = presenter.printDeleteWholeEvent();
        if (name.equals("0")){
            userController.mainMenu();
        }
        else if (!eManager.canRemoveEvent(name)){
            presenter.printInvalidOption();
            removeEvent();
        }
        else{
            Event e = eManager.getEvent(name);
            userManager.cancelWholeEvent(e.getAttending(), name, eManager.getEvent(name).getSpeaker());
            eManager.removeEvent(name);
            roomManager.removeRoomEvent(e.getRoomNum(), name);
            presenter.printEventRemoved();
            userController.mainMenu();
        }
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

    /**
     * prints the information of an event that is given by name
     *
     */
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
