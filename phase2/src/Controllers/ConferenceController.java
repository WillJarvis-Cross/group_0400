package Controllers;
import Presenter.*;
import UseCases.*;

import java.lang.String;

public class ConferenceController {

    private final ConferenceManager conferenceManager;
    private final RoomController roomController;
    private final ConferenceRoomPresenter presenter;

    /**
     * Initializes a conferenceController with the given roomController and conferenceManager
     * @param roomController the roomController to be stored
     * @param conferenceManager The conferenceManager to be stored
     */
    public ConferenceController (RoomController roomController, ConferenceManager conferenceManager) {
        this.conferenceManager = conferenceManager;
        this.roomController = roomController;
        this.presenter = new ConferenceRoomPresenter();
    }

    /**
    Makes a request to create a conference, using the presenter to display options and get inputs. If the request
     was valid, a new conference is created.
     */
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
            String roomNum;
            if (!conferenceManager.checkConferenceExist(nameOfConference)){
                roomNum = null;
            }
            else{
                roomNum = roomController.makeRoomRequest();
            }

            if (roomNum == null || !this.conferenceManager.addRoomToConference(nameOfConference, roomNum)){
                presenter.printInvalidOption();
            }
            makeConferenceRequest();

        }else{
            if (!roomNumber.equals("0")){ // 0 means they are going back to the main menu
                presenter.printInvalidOption();
                makeConferenceRequest();
            }

        }
    }

    /**
     * Prints the available conferences and asks the user which one they want to attend using the presenter.
     * @return String
     */
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
