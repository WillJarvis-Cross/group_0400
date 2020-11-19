package Gateways;

import java.io.*;
import java.util.Hashtable;
import java.util.Set;

import Controllers.*;
import Entities.*;
import UseCases.*;

public class ReadAndWrite {
        public void objectSerialize(String filename, Object controller){
            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream(filename);
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

        public void controllerDeserialize(String filename, String userType, String name){
            try
            {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                switch (userType) {
                    case "1": {
                        UserController loaded = (UserController) in.readObject();
                        new AttendeeController(name, loaded.getUsermanager(),
                                loaded.getEventManager(),
                                loaded.getMessageManager(),
                                loaded.getRoomManager());
                        break;
                    }
                    case "2": {
                        UserController loaded = (UserController) in.readObject();
                        new OrganizerController(name, loaded.getUsermanager(),
                                loaded.getEventManager(),
                                loaded.getMessageManager(),
                                loaded.getRoomManager());
                        break;
                    }
                    case "3": {
                        UserController loaded = (UserController) in.readObject();
                        new SpeakerController(name, loaded.getUsermanager(),
                                loaded.getEventManager(),
                                loaded.getMessageManager(),
                                loaded.getRoomManager());
                        break;
                    }
                }

                in.close();
                file.close();
            }

            catch(IOException ex)
            {
                System.out.println("IOException during loading of serialized file");
                ex.printStackTrace();
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException during loading of serialized file");
                ex.printStackTrace();
            }
        }
    }



