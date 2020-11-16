
/** Contains the main method for the program
 * @auther group 400
 */
import Presenter.*;
import Controllers.*;
import Gateways.*;


import java.util.ArrayList;
import java.lang.String;

/** Represents the main method that will be launched
 * @author group 400
 */
public class Main{
    /**
     * Main method where the contollers will be called from and data will be loaded/saved
     * Kinda messy and incomplete for now, but will finish when gateway class is ready
     * @param args
     */
    public static void main(String[] args){
        Presenter presenter = new Presenter();
        ReadAndWrite readWriter = new ReadAndWrite();

        int input1 = presenter.printLogin();
        //no way of checking/saving anything yet
        name = presenter.printUsername();
        pass = presenter.printPassword();
        if input1.equals("1"){
            //nothing yet, ask for username and password, check if correct
        } else if input1.equals("2"){
            //save info
        }

        //this should only be called if the user is creating their account, otherwise this information would be stored.
        //Temporarily setting things up this way until gateways are done
        String userType = presenter.printAttendeeOrOrganizer(); //will be moved into else if input1.equals("2") later



        if userType.equals("1"){
            AttendeeController aController = new AttendeeController(name);
            aController.mainMenu();
        }  else if userType.equals("2"){
            OrganizerController oController = new OrganizerController(name);
            oController.mainMenu();
        }else if userType.equals("3") {
            SpeakerController sController = new SpeakerController(name);
            sController.mainMenu()
        }
}
