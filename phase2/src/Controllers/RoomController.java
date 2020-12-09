package Controllers;

import Presenter.*;
import UseCases.*;

import java.lang.String;


/** Represents the controller for RoomManager.
 * @author group 400
 */

public class RoomController {

    private final RoomManager roomManager;
    private final RoomEventPresenter presenter;

    /**
     * Creates a new RoomController given the already created managers
     * @param roomManager The RoomManager
     */
    public RoomController (RoomManager roomManager) {
        this.roomManager = roomManager;
        this.presenter = new RoomEventPresenter();
    }

    /**
     * create a room when a create room request is made
     * calls roomManager to check if whether a room with the same number exist or not and creates
     * a room iff it does not exist, otherwise prints invalid request
     */
    public String makeRoomRequest(){
        String roomNumber = presenter.printRoomNumber();
        if (!roomNumber.equals("0")){
            int capacity = presenter.printRoomCapacity();
            int techLevel = presenter.printRoomTech();
            if ((capacity <= 0) || (techLevel < 1) || (techLevel>5)){
                presenter.printInvalidOption();
                makeRoomRequest();
            }
            else{
                if (roomManager.addRoom(roomNumber, capacity, techLevel)){
                    presenter.printRoomAdded();
                    return roomNumber;
                }
                else{
                    presenter.printInvalidOption();
                    makeRoomRequest();
                }
            }
            return roomNumber;
        }
        return null;
    }
}
