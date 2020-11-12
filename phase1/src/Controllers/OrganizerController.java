package Controllers;
import UseCases.*;

import java.time.LocalDateTime;
import Entities.Event;
import java.util.Set;

public class OrganizerController{
    EventManager events;
    UserManager usermanager;
    String name;




    public OrganizerController(String name, String password, UserManager usermanager, EventManager events){
        this.events = events;
        this.usermanager = usermanager;
        if(usermanager.login(name, password) && usermanager.getOrganizers().contains(usermanager.getUser(name))){
            this.name = name;
        }
    }

    public void addEvent(LocalDateTime time, int duration, String speaker,
                         int capacity, String eventName, String roomNumber){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            events.scheduleEvent(time, duration, speaker, capacity, eventName, roomNumber);
        }
    }

    public void addSpeaker(String passwordInput, String nameInput){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            usermanager.addSpeaker(nameInput, passwordInput);
        }
    }

    public void scheduleSpeaker(LocalDateTime time, String speaker, String roomNumber){
        if(name == null){
            System.out.println("Please log in.");
        }
        else{
            Event e = null;
            for(Event event: events.getEvents()) {
                if (event.getTime().equals(time) && event.getRoomNum().equals(roomNumber)) {
                    e = event;
                }
            }
            if(e != null){
                events.setSpeaker(e.getEventName(), speaker);
            }
            else{ System.out.println("No event exists at that time and place. Use addEvent"); }
        }
    }

    public void messageAttendee(String attendeeName, String content){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            MessageManager.sendMessage(usermanager.getOrganizer(name), usermanager.getAttendee(attendeeName), content);
        }
    }

    public void messageAllAttendees(String content){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            Set<String> keys = usermanager.getAttendees().keySet();
            for (String key : keys) {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getAttendee(key), content);
            }
        }
    }

    public void messageSpeaker(String speakerName, String content){
            if(name == null){
                System.out.println("Please log in.");
            }
            else {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getSpeaker(speakerName), content);
            }
    }

    public void messageAllSpeakers(String content){
        if(name == null){
            System.out.println("Please log in.");
        }
        else {
            Set<String> keys = usermanager.getSpeakers().keySet();
            for (String key : keys) {
                MessageManager.sendMessage(usermanager.getOrganizer(name),
                        usermanager.getSpeaker(key), content);
            }
        }
    }

    public void signUp(String eventName){

        usermanager.signUp(name,eventName);

    }

}