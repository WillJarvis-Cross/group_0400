import Controllers.AttendeeController;
import Controllers.OrganizerController;
import Controllers.SpeakerController;
import Controllers.UserController;
import Entities.Room;
import Gateways.ReadAndWrite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates a saveFile that creates a organizer with a handful of attendee, speakers, events and rooms
 * Made easy to add or remove items
 *
 * Prerequisite for generating file:
 * Make sure to comment out the invocations of the following lines from UserController before running this
 * class: <i>makeNewAccount</i> and <i>mainMenu</i> lines 55 and 57 respectively.
 *
 * If there are issues loading the saveFile, you can try deleting / removing it from folder and then generate a new
 * one.
 */
public class GenerateFakeData {
  private int MAX_NUMBER_OF_ROOMS = 50;
  private int MAX_ROOM_SIZE_PER_ROOM = 200;
  private int MAX_TECH_TYPES = 5;
  private int MAX_ROOMS_TO_GEN_PER_USER = 5;
  private int MAX_EVENT_DURATION = 5;
  private int MAX_NUMBER_OF_EVENTS = 5;
  private String[] EVENT_NAME_SAMPLES = {
      "The Outrage", "Keep Out Die In", "Less Is Good?", "Stupid Stuff", "Good stuff bad stuff", "Night City Live",
      "Cyberpunk is awesome"
  };
  private List<String> eventTimes = new ArrayList<>();
  private double MIN_PRICE = 1.5;
  private String[][] SAMPLE_SPEAKERS = {
      {"TheMouther", "lasdf"},
      {"LoudSpeaker", "lasd"},
      {"Silent speaker", "pors"},
      {"Frustrated", "lask"},
      {"Alas", "kale"},
  };
  private String[][] SAMPLE_ATTENDEES = {
      {"Don't hurt", "lasdf"},
      {"Toxic Me", "lasd"},
      {"Bootleg", "pors"},
      {"the bluees", "lask"},
      {"Bells", "kale"},
  };

  public GenerateFakeData() {}

  private int getRandNumber(int maxNumber) {
    Random ran = new Random();
    return ran.nextInt(maxNumber) + 1;
  }

  private double getDoubleRandNumber(double maxNumber) {
    Random ran = new Random();
    return MIN_PRICE + (maxNumber - MIN_PRICE) * ran.nextDouble();
  }

  private UserController createUser(int userType, String name, String password) {
    UserController newUser;
    if (userType == 0) {
      newUser = new OrganizerController(name);
      newUser.getUsermanager().addOrganizer(name, password);
    } else if (userType == 1) {
      newUser = new AttendeeController(name);
      newUser.getUsermanager().addAttendee(name, password);
    } else {
      newUser = new SpeakerController(name);
      newUser.getUsermanager().addSpeaker(name, password);
    }
    return newUser;
  }

  private void scheduleFakeEvent(UserController user, int counter) {
    LocalDateTime time = LocalDateTime.now();
    time.plusDays(getRandNumber(50));
    time.plusHours(getRandNumber(10));

    int duration = getRandNumber(MAX_EVENT_DURATION);

    int getEventAt = counter;
    if (counter < 0) {
      getEventAt = getRandNumber(EVENT_NAME_SAMPLES.length);
    }
    String eventName = EVENT_NAME_SAMPLES[getEventAt];

    List<String> speakers = new ArrayList<>();
    speakers.addAll(user.getUsermanager().getSpeakers().keySet());

    List<String> rooms = new ArrayList<>();
    rooms.addAll(user.getRoomManager().allRooms.keySet());
    String roomName = rooms.get(getRandNumber(rooms.size() - 1));
    Room room = user.getRoomManager().getRoom(roomName);

    int capacity = room.getCapacity();
    int techLevel = room.getTechLevel();

    double price = getDoubleRandNumber(10.0);

    user.getEventManager().scheduleEvent(time, duration, speakers, eventName, roomName, capacity, false, price);
  }

  private void log(String content) {
    System.out.println(content);
  }

  private int[] generateRoomArgs() {
    int[] newInt = {
        getRandNumber(MAX_NUMBER_OF_ROOMS),
        getRandNumber(MAX_ROOM_SIZE_PER_ROOM),
        getRandNumber(MAX_TECH_TYPES),
    };
    return newInt;
  }

  private void createRooms (UserController user) {
    int numRoomsToGen = getRandNumber(MAX_ROOMS_TO_GEN_PER_USER);

    for (int count = 0; count < numRoomsToGen; count++) {
      int[] roomArgs = generateRoomArgs();
      user.getRoomManager().addRoom(Integer.toString(roomArgs[0]), roomArgs[1], roomArgs[2]);
    }
  }

  private void createAndSaveFakeData() {
    log("==== Generating ====");
    String[] usernamePass = {"TheO", "asdf"};

    log("Creating new user");
    UserController newUser = createUser(0, usernamePass[0], usernamePass[1]);

    log("Creating new rooms");
    createRooms(newUser);

    log("Adding speakers");
    for (String[] speaker: SAMPLE_SPEAKERS) {
      newUser.getUsermanager().addSpeaker(speaker[0], speaker[1]);
    }

    log("Adding attendees");
    for (String[] attendee: SAMPLE_ATTENDEES) {
      newUser.getUsermanager().addAttendee(attendee[0], attendee[1]);
    }

    log("Signing up attendee to events");
    for (int count = 0; count < MAX_NUMBER_OF_EVENTS; count++) {
      scheduleFakeEvent(newUser, count);
      String eventName = EVENT_NAME_SAMPLES[count];
      if (count < 3) {
        newUser.getUsermanager().signUp(
            "Bootleg",
            newUser.getEventManager().getEvent(eventName),
            newUser.getEventManager().getEventsByUsername(newUser.getUsermanager().getUser("Bootleg")),
            newUser.getEventManager().getEvent(eventName).getPrice()
        );
      }
    }

    log("Saving users to file");
    ReadAndWrite readWriter = new ReadAndWrite();
    readWriter.objectSerialize(newUser);

    log("======= End  =======");
  }

  public void generateAndSave() {
    createAndSaveFakeData();
  }
}
