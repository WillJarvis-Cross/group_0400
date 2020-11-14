package Gateways;

import java.io.*;
import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import Entities.*;
import UseCases.*;

public class ReadAndWrite {
        public void helperSerialize(String filename, ArrayList information){
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
        public ArrayList helperDeseriialize(String filename){

            ArrayList object = null;

            try
            {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                object = (ArrayList)in.readObject();

                in.close();
                file.close();


                return object;


            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
                return object;
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException is caught");
                return object;
            }

        }

        // complete this method after adding necessary methods to usermanager



        public void serializeInfo(ArrayList organizerList,ArrayList speakerList, ArrayList attendeeList, ArrayList
                messagesList,ArrayList roomList, ArrayList eventList,  String organizerFile, String attendeeFile,
                                  String speakerFile, String messageFile, String roomFile, String eventFile ){

            helperSerialize(organizerFile,organizerList);
            helperSerialize(speakerFile,speakerList);
            helperSerialize(attendeeFile,attendeeList);
            helperSerialize(messageFile,messagesList);
            helperSerialize(roomFile,roomList);
            helperSerialize(eventFile,eventList);



        }



        public void deserializeUserInformation(String attendeeFile,String organizerFile, String speakerFile, UserManager Manager){
            ArrayList attendeeInfo = helperDeseriialize(attendeeFile);
            ArrayList organizerInfo = helperDeseriialize(organizerFile);
            ArrayList speakerInfo = helperDeseriialize(speakerFile);
            for (int i = 0; i < attendeeInfo.size(); i++) {
                // add messages directly to array from the manager

            }


        }

        public void deserializeMessageInformation(String messageFile, MessageManager manager){
            ArrayList allMessages = helperDeseriialize(messageFile);
            for (int i = 0; i < allMessages.size(); i++) {
                // add messages directly to array from the manager
            }

        }

        public void deserializeRoomInfo(String roomFile, RoomManager manager){
            ArrayList allRooms = helperDeseriialize(roomFile);
            for (int i = 0; i < allRooms.size(); i++) {
                // add messages directly to array from the manager
            }


        }


        //possibly implement an interface for manager classes? allows us to use a common 'add' method common to all manager
        //so we could simplify deserialize to just one method!



    }



