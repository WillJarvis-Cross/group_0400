package Controllers;

import Presenter.*;
import UseCases.EventManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.lang.String;
import java.util.ArrayList;

import java.time.LocalDateTime;

/** Represents the controller for EventManager
 * @author group 400
 */
public class EventController {

    private final EventManager eManager;
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final UserController userController;
    private final RoomEventPresenter presenter;

    /**
     * initialize a clean EventController with given UserController,
     * EventManager, UserManager, Presenter, and RoomManager
     */
    public EventController(UserController userController, EventManager eManager,
                           UserManager userManager, RoomManager roomManager){
        this.eManager = eManager;
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.userController = userController;
        this.presenter = new RoomEventPresenter();
    }

    private boolean isSpeakerInUsers(ArrayList<String> speakers){
        for (int i = 0; i < speakers.size(); i++) {
            if (!userManager.getSpeakers().containsKey(speakers.get(i))){
                return false;

            }



        }
        return true;

    }

    private boolean canAddSpeaker(ArrayList<String> speakers, LocalDateTime time){
        for (int i = 0; i < speakers.size(); i++) {
            if(!userManager.canAddSpeaker(time, eManager.getEventsByUsername(userManager.getUser(speakers.get(i))))){
                return false;

            }


        }
        return true;

    }

    private void signUp(ArrayList<String> speaker, String eventName){
        for (int i = 0; i < speaker.size(); i++)
            userManager.signUp(speaker.get(i), eManager.getEvent(eventName), eManager.getEventsExceptOne(userManager.getUser(speaker.get(i)), eManager.getEvent(eventName)));

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
            ArrayList<String> speaker = presenter.printSpeakerOfEvent();
            int techLevel = presenter.printTechEvent();
            presenter.printRoomsSuitableTech(roomManager.getRoomsByTech(techLevel));
            String roomNumber = presenter.printRoomNumber();
            int capacity = presenter.printEventCapacity();
            boolean boolVIP = presenter.printVIP();
            int counter = 0;
            if (roomManager.getRoom(roomNumber) != null) {
                if (!isSpeakerInUsers(speaker)|| roomManager.getRoom(roomNumber) == null) {
                    counter++;
                }
                if (roomManager.isRoomTaken(roomNumber, time)) {
                    counter++;
                }
                if (!eManager.canScheduleEvent(time, duration, speaker, eventName, roomNumber, capacity, boolVIP, techLevel) || duration == 0) {
                    counter++;
                }
                if (capacity > roomManager.getRoom(roomNumber).getCapacity()) {
                    counter++;
                }
                if (techLevel > roomManager.getRoom(roomNumber).getTechLevel()) {
                    counter++;
                }
                if (!isSpeakerInUsers(speaker) || ! canAddSpeaker(speaker,time)) {
                    counter++;
                }
            }
            else{
                counter++;
            }
            if (counter == 0){
                eManager.scheduleEvent(time, duration, speaker, eventName, roomNumber, capacity,boolVIP, techLevel);
                signUp(speaker,eventName);
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
        if (roomManager.getRoom(eManager.getEvent(eventName).getRoomNum()) == null){
            return false;
        }
        if (this.eManager.isAtCapacity(eventName)){
            return false;
        }
        if (!eManager.containsEvent(eventName)){
            return false;
        }
        if (!userManager.canSignUp(eManager.getEvent(eventName), eManager.getEventsByUsername(userManager.getUser(username)))){
            return false;
        }
        if(!userManager.checkVIPSignUp(eManager.getEvent(eventName), userManager.getUser(username))){
            return false;
        }
        userManager.signUp(username, eManager.getEvent(eventName), eManager.getEventsByUsername(userManager.getUser(username)));
        eManager.addPersonToEvent(eventName, username);

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
        else if (!eManager.containsEvent(name)){
            presenter.printInvalidOption();
            removeEvent();
        }
        else{
            userManager.cancelWholeEvent(eManager.getEvent(name).getAttending(), name, eManager.getEvent(name).getSpeaker());
            roomManager.removeRoomEvent(eManager.getEvent(name).getRoomNum(), name);
            eManager.removeEvent(name);
            presenter.printEventRemoved();
            userController.mainMenu();
        }
    }

    /**
     * Change the capacity of an event
     *
     */
    public void changeEventCapacity(){
        presenter.printAllEvents(eManager.getEvents());
        String name = presenter.printChangeEventCapacity();
        if (name.equals("0")){
            userController.mainMenu();
        }
        else if (!eManager.containsEvent(name)){
            presenter.printInvalidOption();
            changeEventCapacity();
        }
        else{
            int capacity = presenter.printEventCapacity();
            if (capacity > roomManager.getRoom(eManager.getEvent(name).getRoomNum()).getCapacity()) {
                presenter.printInvalidOption();
                changeEventCapacity();
            }
            else{
                eManager.getEvent(name).setCapacity(capacity);
            }
            userController.mainMenu();
        }
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
