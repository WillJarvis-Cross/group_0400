import Entities.Event;

import java.time.LocalDateTime;

public class EventManager {
}

//All this code here is for adding an event to a user's list of events. I originally made it for the Event entity
//but it doesn't rlly belong there. So use it if u want or dont. I think there is a better wat to do it tho.
/*
    // Takes in an event to add to the list of events. If the event does not overlap with any other events, then
    // it adds the event to the array list in chronological order and returns true. Otherwise it returns false.
    public boolean addEvent(Event newEvent){
        LocalDateTime thisTime = newEvent.getTime();
        LocalDateTime timePlusDuration = thisTime.plusHours(newEvent.getDuration());
        int i = 0;
        // This while loop finds the position as which the new event will be added
        while (i < events.size()){
            if (events.get(i).getTime().compareTo(thisTime) >= 0){
                break;
            }
            i++;
        }

        if (i == 0){ // This is the case where the event is being added to the beginning of the list
            if (events.size() == 0){
                events.add(newEvent);
                return true;
            }
            // Checking to see that the event does not overlap with the event in front of it.
            // .compareTo() returns a positive integer when timePlusDuration is before the time of the event
            // So checking this makes sure the no duration of the event overlaps
            if (events.get(i).getTime().compareTo(timePlusDuration) >= 0){
                events.add(i, newEvent);
                return true;
            }
        }
        else if (i == events.size()){ // This is the case where the event is being added to the end of the list.
            Event eventBefore = events.get(i - 1);
            LocalDateTime timeWithDuration = eventBefore.getTime().plusHours(eventBefore.getDuration());

            // Checking to see that the event does not overlap with the event behind it.
            // .compareTo() returns a negative integer when thisTime is ahead of the end time of the event
            if (timeWithDuration.compareTo(thisTime) <= 0){
                events.add(newEvent);
                return true;
            }
        }
        else{
            Event eventBefore = events.get(i - 1);
            Event eventAfter = events.get(i);
            LocalDateTime timeWithDuration = eventBefore.getTime().plusHours(eventBefore.getDuration());

            // Checking to see that the event does not overlap with the event behind it or in front of it.
            if (timeWithDuration.compareTo(thisTime) <= 0 && eventAfter.getTime().compareTo(timePlusDuration) >= 0){
                events.add(i, newEvent);
                return true;
            }
        }
        return false;
    }

 */