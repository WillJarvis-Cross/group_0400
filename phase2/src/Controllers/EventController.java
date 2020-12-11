package Controllers;

import Entities.Event;
import Presenter.*;
import UseCases.ConferenceManager;
import UseCases.EventManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.lang.String;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/** Represents the controller for EventManager
 * @author group 400
 */
public class EventController {

    private final EventManager eManager;
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final RoomEventPresenter presenter;
    private final ConferenceManager conferenceManager;
    /**
     * initialize a clean EventController with given
     * EventManager, UserManager, Presenter, and RoomManager
     */
    public EventController(EventManager eManager,
                           UserManager userManager, RoomManager roomManager, ConferenceManager conferenceManager){
        this.eManager = eManager;
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.presenter = new RoomEventPresenter();
        this.conferenceManager = conferenceManager;
    }


    /**
     * Returns true if the accounts for the given speakers exist, and false otherwise
     * @param speakers The list of speakers to check for
     * @return true or false
     */
    private boolean isSpeakerInUsers(List<String> speakers){
        for (String speaker : speakers) {
            if (userManager.canAddPerson(speaker)) {
                return false;
            }
        }
        return true;

    }

    /**
     * Returns true if the speaker(s) can be added to an event with the given time and duration, and false otherwise.
     * @param speakers The speakers to add for the event
     * @param time The starting time of the event
     * @param duration The duration of the event, in hours
     * @return true or false
     */
    private boolean canAddSpeaker(List<String> speakers, LocalDateTime time, int duration){
        for (String speaker : speakers) {
            if (!userManager.canSignUp(time, duration,
                    eManager.getEventsByUsername(userManager.getUser(speaker)))) {
                return false;

            }

        }
        return true;

    }

    /**
     * Signs up the speakers for the event of the given name
     * @param speaker the speakers who will be signed up for an event
     * @param eventName the event the speaker will sign up for
     */
    private void signUp(List<String> speaker, String eventName){
        for (String s : speaker) {
            userManager.signUp(s, eManager.getEvent(eventName),
                    eManager.getEventsExceptOne(userManager.getUser(s), eManager.getEvent(eventName)),
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
            String roomNumber = presenter.printRoomNumber(false);
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
                if (!eManager.canScheduleEvent(time, duration, speaker, eventName, roomNumber, capacity, boolVIP, price) || duration == 0) {
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
                eManager.scheduleEvent(time, duration, speaker, eventName, roomNumber, capacity,boolVIP, price);
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
    public List<String> topFiveAttendedEvents(){
        List<String> list = new ArrayList<>();

        List<Event> copy = new ArrayList<>(eManager.getEvents());
        Collections.sort(copy);
        Collections.reverse(copy);
        int i = 0;
        while(i<5 && i < copy.size()){
            list.add(copy.get(i).getEventName());
            i++;
        }
        return list;
    }

    /**
     * Returns a list of events taking place at the given conference
     * @param conference the name of the conference to get the list of events from
     * @return List of event names
     */
    public List<String> getEventByConference(String conference) {

        List<String> roomNames = this.conferenceManager.getConference(conference).getRoom();
        List<String> eventNames = new ArrayList<>();
        for (String temp: roomNames){
            eventNames.addAll(this.roomManager.getEventByRooms(temp));
        }
        return eventNames;

    }
    /**
     * Return Returns the Room Event Presenter

     * @return RoomEventPResenter
     */

    public RoomEventPresenter getEventPresenter(){
        return presenter;
    }

}
