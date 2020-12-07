package Controllers;
import Presenter.*;
import UseCases.*;

import java.lang.String;

public class ConferenceController {

    private final ConferenceManager conferenceManager;
    private final RoomController roomController;
    private final ConferenceRoomPresenter presenter;
    private final UserController userController;

    public ConferenceController (ConferenceController conferenceController, RoomController roomController, ConferenceManager conferenceManager,UserController userController) {
        this.conferenceManager = conferenceManager;
        this.roomController = roomController;
        this.userController = userController;
        this.presenter = new ConferenceRoomPresenter();
    }

    public void makeConferenceRequest(){
        String roomNumber = presenter.printNameOfConference();
        if (roomNumber.equals("0")){
            userController.mainMenu();
        }
        else{
            String nameOfConference = presenter.printNameOfConference();
            if(conferenceManager.addConference(nameOfConference)){
                presenter.printConferenceAdded();
            }
        }
    }
}
