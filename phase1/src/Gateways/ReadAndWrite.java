package Gateways;

import java.io.*;
import java.util.Hashtable;
import java.util.Set;

import Entities.*;
import UseCases.*;

public class ReadAndWrite {
        private void helperSerialize(String filename, Hashtable<String, User> information){
            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(information);

                out.close();
                file.close();

            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }

        }
        private Hashtable<String, User> helperDeseriialize(String filename){

            Hashtable<String, User> object = null;

            try
            {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                object = (Hashtable<String, User>) in.readObject();

                in.close();
                file.close();


                return object;


            }

            catch(IOException ex)
            {
                System.out.println("IOException during loading of serialized file");
                return object;
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException during loading of serialized file");
                return object;
            }

        }

        // complete this method after adding necessary methods to usermanager

        //TODO: Test the following code to ensure it works with other objects
        // also modify to work with userController once implemented
        public void save(UserManager users){
            this.helperSerialize("saveFile", users.getUsers());
        }

        public void load(UserManager users){
            Hashtable<String,User> loadedTable = this.helperDeseriialize("saveFile");
            if (loadedTable != null) {

                Set<String> keys = loadedTable.keySet();
                for (String key: keys){

                    //TODO: This code design is not great and suggests we need to use some polymorphism.
                    // not great but it works
                    if (loadedTable.get(key) instanceof Attendee){
                        users.addAttendee((Attendee)loadedTable.get(key));
                    } else if (loadedTable.get(key) instanceof Speaker){
                        users.addSpeaker((Speaker) loadedTable.get(key));
                    } else {
                        users.addOrganizer((Organizer) loadedTable.get(key));
                    }
                }
            }
        }


//TODO: Just keeping this code in case we need it, should decide if we want to keep it or not
//
//        public void serializeInfo(ArrayList organizerList,ArrayList speakerList, ArrayList attendeeList, ArrayList
//                messagesList,ArrayList roomList, ArrayList eventList,  String organizerFile, String attendeeFile,
//                                  String speakerFile, String messageFile, String roomFile, String eventFile ){
//
//            helperSerialize(organizerFile,organizerList);
//            helperSerialize(speakerFile,speakerList);
//            helperSerialize(attendeeFile,attendeeList);
//            helperSerialize(messageFile,messagesList);
//            helperSerialize(roomFile,roomList);
//            helperSerialize(eventFile,eventList);
//
//
//
//        }
//
//
//
//        public void deserializeUserInformation(String attendeeFile,String organizerFile, String speakerFile, UserManager Manager){
//            ArrayList attendeeInfo = helperDeseriialize(attendeeFile);
//            ArrayList organizerInfo = helperDeseriialize(organizerFile);
//            ArrayList speakerInfo = helperDeseriialize(speakerFile);
//            for (int i = 0; i < attendeeInfo.size(); i++) {
//                // add messages directly to array from the manager
//
//            }
//
//
//        }
//
//        public void deserializeMessageInformation(String messageFile, MessageManager manager){
//            ArrayList allMessages = helperDeseriialize(messageFile);
//            for (int i = 0; i < allMessages.size(); i++) {
//                // add messages directly to array from the manager
//            }
//
//        }
//
//        public void deserializeRoomInfo(String roomFile, RoomManager manager){
//            ArrayList allRooms = helperDeseriialize(roomFile);
//            for (int i = 0; i < allRooms.size(); i++) {
//                // add messages directly to array from the manager
//            }
//
//
//        }


        //possibly implement an interface for manager classes? allows us to use a common 'add' method common to all manager
        //so we could simplify deserialize to just one method!



    }



