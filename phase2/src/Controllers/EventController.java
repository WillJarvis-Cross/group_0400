package Controllers;

import Entities.Event;
import Presenter.*;
import UseCases.EventManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.lang.String;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/** Represents the controller for EventManager
 * @author group 400
 */
public class EventController {

    private final EventManager eManager;
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final RoomEventPresenter presenter;

    /**
     * initialize a clean EventController with given
     * EventManager, UserManager, Presenter, and RoomManager
     */
    public EventController(EventManager eManager,
                           UserManager userManager, RoomManager roomManager){
        this.eManager = eManager;
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.presenter = new RoomEventPresenter();
    }

    public ArrayList<Event> getListOfEvents() {
        return (ArrayList<Event>) eManager.getEvents();
    }

    private boolean isSpeakerInUsers(List<String> speakers){
        for (int i = 0; i < speakers.size(); i++) {
            if (!userManager.getSpeakers().containsKey(speakers.get(i))){
                return false;
            }
        }
        return true;

    }

    private boolean canAddSpeaker(List<String> speakers, LocalDateTime time, int duration){
        for (int i = 0; i < speakers.size(); i++) {
            if(!userManager.canSignUp(time, duration,
                    eManager.getEventsByUsername(userManager.getUser(speakers.get(i))))){
                return false;

            }

        }
        return true;

    }

    private void signUp(List<String> speaker, String eventName){
        for (int i = 0; i < speaker.size(); i++) {
            userManager.signUp(speaker.get(i), eManager.getEvent(eventName),
                    eManager.getEventsExceptOne(userManager.getUser(speaker.get(i)), eManager.getEvent(eventName)),
                    eManager.getEvent(eventName).getPrice());
        }
    }


    /**
     * create a event when a create event request is made
     * calls eventManager, userManager, and roomManager to check if event can be created with the information
     */


    public void makeEventRequest(){
        String eventName = presenter.printNameOfEvent();

        if (!eventName.equals("0")){
            LocalDateTime time = presenter.printTimeOfEvent();
            int duration = presenter.printDurationOfEvent();
            List<String> speaker = presenter.printSpeakerOfEvent();
            int techLevel = presenter.printTechEvent();
            presenter.printRoomsSuitableTech(roomManager.getRoomsByTech(techLevel));
            String roomNumber = presenter.printRoomNumber();
            int capacity = presenter.printEventCapacity();
            boolean boolVIP = presenter.printVIP();
            int counter = 0;
            double price = presenter.printPrice();
            if (roomManager.getRoom(roomNumber) != null) {
                if (!isSpeakerInUsers(speaker)|| roomManager.getRoom(roomNumber) == null) {
                    counter++;
                }
                if (roomManager.isRoomTaken(roomNumber, time)) {
                    counter++;
                }
                if (!eManager.canScheduleEvent(time, duration, speaker, eventName, roomNumber, capacity, boolVIP, techLevel, price) || duration == 0) {
                    counter++;
                }
                if (capacity > roomManager.getRoom(roomNumber).getCapacity()) {
                    counter++;
                }
                if (techLevel > roomManager.getRoom(roomNumber).getTechLevel()) {
                    counter++;
                }
                if (!isSpeakerInUsers(speaker) || ! canAddSpeaker(speaker,time, duration)) {
                    counter++;
                }
            }
            else{
                counter++;
            }
            if (counter == 0){
                eManager.scheduleEvent(time, duration, speaker, eventName, roomNumber, capacity,boolVIP, techLevel, price);
                signUp(speaker,eventName);
                roomManager.addEvent(roomNumber, eventName, time);
                presenter.printEventCreated();
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
        if (!userManager.canSignUp(eManager.getEvent(eventName).getTime(), eManager.getEvent(eventName).getDuration(),
                eManager.getEventsByUsername(userManager.getUser(username)))){
            return false;
        }
        if(!userManager.checkVIPSignUp(eManager.getEvent(eventName), userManager.getUser(username))){
            return false;
        }
        userManager.signUp(username, eManager.getEvent(eventName), eManager.getEventsByUsername(userManager.getUser(username)),
                eManager.getEvent(eventName).getPrice());
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
        if (!name.equals("0")){
            if (!eManager.containsEvent(name)){
                presenter.printInvalidOption();
                removeEvent();
            }
            else{
                userManager.cancelWholeEvent(eManager.getEvent(name).getAttending(), name, eManager.getEvent(name).getSpeaker(), eManager.getEvent(name).getPrice());
                roomManager.removeRoomEvent(eManager.getEvent(name).getRoomNum(), name);
                eManager.removeEvent(name);
                presenter.printEventRemoved();
            }
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
            if (!eManager.containsEvent(name)){
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
            }
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
    }

    /**
     * Returns a List of the top 5 events with most people signed up
     *
     */
    public ArrayList<String> topFiveAttendedEvents(){
        ArrayList<String> list = new ArrayList<String>();

        ArrayList<Event> copy = new ArrayList<Event>(eManager.getEvents());
        Collections.sort(copy);
        Collections.reverse(copy);
        int i = 0;
        while(i<5 && i < copy.size()){
            list.add(copy.get(i).getEventName());
            i++;
        }
        return list;
    }
}
