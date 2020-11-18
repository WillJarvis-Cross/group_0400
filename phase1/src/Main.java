
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
     * Main method where the controllers will be called from and data will be loaded/saved
     * Kinda messy and incomplete for now, but will finish when gateway class is ready
     * @param args
     */
    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        ReadAndWrite readWriter = new ReadAndWrite();
        String userType = presenter.printAttendeeOrOrganizer();
        String name = presenter.printUsername();
        if (userType.equals("1")) {
            new AttendeeController(name);
        } else if (userType.equals("2")) {
            new OrganizerController(name);
        } else if (userType.equals("3")) {
            new SpeakerController(name);
        }
    }
}
