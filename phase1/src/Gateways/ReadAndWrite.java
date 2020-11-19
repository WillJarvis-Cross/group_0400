package Gateways;

import java.io.*;

import Controllers.*;

/** This is an entity for a Message which contains the content of the message, the name of the users who
 * sent and received the message, and the id of the message
 * @author group 0400
 */
public class ReadAndWrite {
    private static final String fileName = "saveFile";
        public void objectSerialize(Object controller){
            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(controller);

                out.close();
                file.close();

            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
                ex.printStackTrace();
            }

        }

        public UserController controllerDeserialize(String userType, String name){
            try
            {
                UserController user = null;
                // Reading the object from a file
                FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(file);

                switch (userType) {
                    case "1": {
                        UserController loaded = (UserController) in.readObject();
                        user = new AttendeeController(name, loaded.getUsermanager(),
                                loaded.getEventManager(),
                                loaded.getMessageManager(),
                                loaded.getRoomManager());
                        break;
                    }
                    case "2": {
                        UserController loaded = (UserController) in.readObject();
                        user = new OrganizerController(name, loaded.getUsermanager(),
                                loaded.getEventManager(),
                                loaded.getMessageManager(),
                                loaded.getRoomManager());
                        break;
                    }
                    case "3": {
                        UserController loaded = (UserController) in.readObject();
                        user = new SpeakerController(name, loaded.getUsermanager(),
                                loaded.getEventManager(),
                                loaded.getMessageManager(),
                                loaded.getRoomManager());
                        break;
                    }
                }

                in.close();
                file.close();
                return user;
            }

            catch(IOException ex)
            {
                System.out.println("IOException during loading of serialized file");
                ex.printStackTrace();
                return null;
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException during loading of serialized file");
                ex.printStackTrace();
                return null;
            }
        }
    }



