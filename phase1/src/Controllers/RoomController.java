package Controllers;

import Presenter.Presenter;
import UseCases.*;

import java.lang.String;


/** Represents the controller for RoomManager.
 * @author group 400
 */

public class RoomController {

    private final RoomManager roomManager;
    private final UserController userController;
    private final Presenter presenter;

    /**
     * Creates a new RoomController given the already created managers
     * @param userController The UserController
     * @param presenter The Presenter
     * @param roomManager The RoomManager
     */
    public RoomController (UserController userController, Presenter presenter, RoomManager roomManager) {
        this.roomManager = roomManager;
        this.userController = userController;
        this.presenter = presenter;
    }

    /**
     * create a room when a create room request is made
     * calls roomManager to check if whether a room with the same number exist or not and creates
     * a room iff it does not exist, otherwise prints invalid request
     */
    public void makeRoomRequest(){
        String roomNumber = presenter.printRoomNumber();
        if (roomNumber.equals("0")){
            userController.mainMenu();
        }
        else{
            int capacity = presenter.printRoomCapacity();
            if (roomManager.addRoom(roomNumber, capacity)){
                presenter.printRoomAdded();
                userController.mainMenu();
            }
            else{
                presenter.printInvalidOption();
                makeRoomRequest();
            }
        }
    }

    /*private ArrayList<Room> getRoomList () {
        ArrayList<Room> roomList = new ArrayList<Room>();
        Hashtable<String, Room> roomHash = this.roomManager.allRooms;
        Set<String> keys = roomHash.keySet();
        for(String key: keys){
            roomList.add(roomHash.get(key));
        }
        return roomList;
    }*/


    /**
     * when the program exist store all information needed to gateway
     *
     * @return a string of all event information to gateway to store
     */
    /*public String writeFileRequest(){
        ArrayList<Room> roomList = getRoomList();
        String outString = "";
        for (int i = 0; i < roomList.size() ;i++){
            outString += roomList.get(i).toString()+",";
        }
        return outString;
    }*/

}
