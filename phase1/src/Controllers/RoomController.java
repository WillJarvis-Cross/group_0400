package Controllers;

import Entities.*;
import Presenter.Presenter;
import UseCases.*;


import java.util.ArrayList;
import java.lang.String;

import java.time.LocalDateTime;

/** Represents the controller for RoomManager.
 * @author group 400
 */

public class RoomController {

    private EventManager eManager;
    private UserManager userManager;
    private RoomManager roomManager;

    private UserController userController;
    private Presenter presenter;

    /**
     * initialize a clean RoomController with new EventManager, UserManager, and RoomManager.
     */
    public RoomController (UserController userController, Presenter presenter) {
        eManager = new EventManager();
        userManager = new UserManager();
        roomManager = new RoomManager();
        this.userController = userController;
        this.presenter = presenter;
    }

    public void setRoomController(RoomManager room){
        roomManager = room;
    }

    public RoomManager getManager() {
        return roomManager;
    }

    /**
     * create a room when a create room request is made
     * calls roomManager to check if whether a room with the same number exist or not and creates
     * a room iff it does not exist, otherwise prints invalid request
     * @return True if room is created, false if room cannot be created with the invalid input
     */
    public void makeRoomRequest(){
        String roomNumber = presenter.printRoomNumber();
        if (roomNumber.equals("0")){
            userController.mainMenu();
        }
        LocalDateTime time = presenter.printTimeOfEvent();
        String eventName = presenter.printNameOfEvent();
        if (roomManager.getRoom(roomNumber)){
            presenter.printInvalidOption();
            makeRoomRequest();
        }
        else{
            roomManager.addEvent(roomNumber, eventName, time);
        }
    }

    /**
     * add event to room
     * First checks if the exists and whether there room has an event in the same timeslot.
     * Returns true if event added successfully, otherwise false
     *
     * @param event The event to add
     * @return True if event is added; otherwise false
     */
    public boolean addEvent(Event event){
        String roomNumber = event.getRoomNum();
        LocalDateTime time = event.getTime();
        String eventName = event.getEventName();
        if (roomNumber) {
            if (!roomManager.isRoomTaken(roomNumber, time)) {
                roomManager.addEvent(roomNumber, eventName, time);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * add event to room
     * First checks if the exists and whether there room has an event in the same timeslot.
     * Returns true if event added successfully, otherwise false
     *
     * @param event The event to remove
     * @return True if event is added; otherwise false
     */
    public boolean removeEvent(Event event){
        String roomNumber = event.getRoomNum();
        String eventName = event.getEventName();
        if (roomNumber) {
            roomManager.removeEvent(roomNumber, eventName);
            return true;
        }
        return false;
    }

    private ArrayList<Room> getRoomList () {
        ArrayList<Room> roomList = new ArrayList<Room>();
        Hashtable<String, Room> roomHash = this.roomManager.allRooms;
        Set<String> keys = roomHash.keySet();
        for(String key: keys){
            roomList.add(roomHash.get(key));
        }
        return roomList;
    }


    /**
     * when the program exist store all information needed to gateway
     *
     * @return a string of all event information to gateway to store
     */
    public String writeFileRequest(){
        ArrayList<Room> roomList = getRoomList();
        String outString = "";
        for (int i = 0; i < roomList.size() ;i++){
            outString += roomList.get(i).toString()+",";
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
