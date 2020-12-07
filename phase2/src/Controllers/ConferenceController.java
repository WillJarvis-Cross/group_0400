package Controllers;
import Presenter.*;
import UseCases.*;

import java.lang.String;

public class ConferenceController {

    private final ConferenceManager conferenceManager;
    private final RoomController roomController;
    private final ConferenceRoomPresenter presenter;
    private final UserController userController;

    public ConferenceController (RoomController roomController, ConferenceManager conferenceManager,UserController userController) {
        this.conferenceManager = conferenceManager;
        this.roomController = roomController;
        this.userController = userController;
        this.presenter = new ConferenceRoomPresenter();
    }

    public ConferenceController(UserController userController, RoomController roomController, ConferenceManager conferenceManager) {
        this.conferenceManager = conferenceManager;
        this.roomController = roomController;
        this.userController = userController;
        this.presenter = new ConferenceRoomPresenter();
    }

    public void makeConferenceRequest(){
        String roomNumber = presenter.conferenceMain();
        if (roomNumber.equals("0")){
            userController.mainMenu();
        }
        else if(roomNumber.equals("1")){   //make conference
            String nameOfConference = presenter.printNameOfConference();
            if(conferenceManager.addConference(nameOfConference)){
                presenter.printConferenceAdded();
                makeConferenceRequest();
            }
            else{
                presenter.printInvalidOption();
                makeConferenceRequest();
            }

        }
        else if(roomNumber.equals("2")){ //add room
            String nameOfConference = presenter.printNameOfConference();
            String roomNum = roomController.makeRoomRequest();
            this.conferenceManager.addRoomToConference(nameOfConference, roomNum);
            makeConferenceRequest();

        }else if(roomNumber.equals("3")){ //remove conference
            String nameOfConference = presenter.printNameOfConference();
            this.conferenceManager.removeConference(nameOfConference);
            makeConferenceRequest();
        }else{
            presenter.printInvalidOption();
            makeConferenceRequest();
        }
    }
}
