package Controllers;
import Presenter.*;
import UseCases.*;

import java.lang.String;

public class ConferenceController {

    private final ConferenceManager conferenceManager;
    private final RoomController roomController;
    private final ConferenceRoomPresenter presenter;

    public ConferenceController (RoomController roomController, ConferenceManager conferenceManager) {
        this.conferenceManager = conferenceManager;
        this.roomController = roomController;
        this.presenter = new ConferenceRoomPresenter();
    }

    public void makeConferenceRequest(){
        String roomNumber = presenter.conferenceMain();

        if(roomNumber.equals("1")){   //make conference
            String nameOfConference = presenter.printNameOfConference(null);
            if(conferenceManager.addConference(nameOfConference)){
                presenter.printConferenceAdded();
            }
            else{
                presenter.printInvalidOption();
            }
            makeConferenceRequest();

        }
        else if(roomNumber.equals("2")){ //add room
            String nameOfConference = presenter.printNameOfConference(conferenceManager.getConferences());
            String roomNum = roomController.makeRoomRequest();
            if (roomNum == null || !this.conferenceManager.addRoomToConference(nameOfConference, roomNum)){
                presenter.printInvalidOption();
            }
            makeConferenceRequest();

        }/*else if(roomNumber.equals("3")){ //remove conference
            String nameOfConference = presenter.printNameOfConference(conferenceManager.getConferences());
            if (!this.conferenceManager.removeConference(nameOfConference)){
                presenter.printInvalidOption();
            }
            else{
                presenter.printConferenceRemoved();
            }
            makeConferenceRequest();
        }*/else{
            if (!roomNumber.equals("0")){ // 0 means they are going back to the main menu
                presenter.printInvalidOption();
                makeConferenceRequest();
            }

        }
    }

    public String conferenceMenu(){
        if (conferenceManager.getConferences().size() == 0){
            presenter.printNoConferences();
            return null;
        }
        else{
            String thisConference;
            while (true){
                thisConference = presenter.printNameOfConference(conferenceManager.getConferences());
                if (conferenceManager.checkConferenceExist(thisConference)){
                    break;
                }
                presenter.printInvalidOption();
            }
            return thisConference;
        }

    }

}
