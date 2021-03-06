import Presenter.*;
import Controllers.*;
import Gateways.*;

import java.lang.String;

/** Represents the main method that will be launched
 * @author group 400
 */
public class Main{
    /**
     * Main method where the controllers will be called from and data will be loaded/saved
     *
     * @param args
     */
    public static void main(String[] args) {
        UserController user = null;
        MainPresenter presenter = new MainPresenter();
        ReadAndWrite readWriter = new ReadAndWrite();
        do{
            String userType = "";
            while (!userType.equals("1") && !userType.equals("2") && !userType.equals("1") && !userType.equals("3") && !userType.equals("4")) {
                userType = presenter.printAttendeeOrOrganizer();
                if (!userType.equals("1") && !userType.equals("2") && !userType.equals("1") && !userType.equals("3") && !userType.equals("4")) {
                    presenter.printInvalid();
                }
            }
            String name = presenter.printUsername();

            if (presenter.loadFromSave()) { // when the user wants to load saved files
                user = readWriter.controllerDeserialize(userType, name);
            } else {
                switch (userType) {
                    case "1":
                        user = new AttendeeController(name);
                        break;
                    case "2":
                        user = new OrganizerController(name);
                        break;
                    case "3":
                        user = new SpeakerController(name);
                        break;
                    case "4":
                        user = new VIPController(name);
                        break;
                }
            }
            if (presenter.saveWhenExit()) { //the user wants to save their file
                readWriter.objectSerialize(user);
            }
        } while(presenter.relog());
    }
}
