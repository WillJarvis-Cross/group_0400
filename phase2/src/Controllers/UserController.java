package Controllers;

import Presenter.*;
import UseCases.*;
import Entities.Event;
import Gateways.ExportHTML;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public abstract class UserController implements Serializable {

     private final UserManager usermanager;
     private transient final UserPresenter presenter;
     private transient final MenuPresenter menuPresenter;
     private transient final EventController eventController;
     private transient final MessageController messageController;
     private transient final ConferenceController conferenceController;

     private final EventManager eventManager;
     private final MessageManager messageManager;
     private final RoomManager roomManager;
     private final ConferenceManager conferenceManager;
     private final GroupChatManager groupChatManager;
     private transient final RoomController roomController;
     private transient final GroupChatController groupChatController;
     private String name;
     public static int usersLoggedIn = 0;
     public static int eventSignups = 0;
     public String myConference;

     /**
      * Creates an instance of UserController with the given name
      * Also initializes the other controllers and use cases
      * When its done initializing it calls makeNewAccount which is the login/make new account screen for the user
      * @param name Name of the user
      */
     public UserController(String name){
          presenter = new UserPresenter();
          menuPresenter = new MenuPresenter();
          usermanager = new UserManager();
          eventManager = new EventManager();
          roomManager = new RoomManager();
          conferenceManager = new ConferenceManager();
          messageManager = new MessageManager();
          groupChatManager = new GroupChatManager(messageManager);
          eventController = new EventController(eventManager, usermanager, roomManager,conferenceManager);
          messageController = new MessageController(usermanager, messageManager, eventManager);
          roomController = new RoomController(roomManager);
          conferenceController = new ConferenceController(roomController,conferenceManager);
          this.groupChatController = new GroupChatController(usermanager, groupChatManager,
                  messageManager);
          this.name = name;
          makeNewAccount();
          myConference = conferenceController.conferenceMenu();
          mainMenu();
     }

     /**
      * This constructor is used for when we are loading a file
      * @param name Name of the User
      * @param userManager The UserManager to be stored
      * @param eventManager The EventManager to be stored
      * @param messageManager The MessageManager to be stored
      * @param roomManager The RoomManager to be stored
      */
     public UserController(String name, UserManager userManager, EventManager eventManager,
                           MessageManager messageManager, RoomManager roomManager, GroupChatManager groupChatManager, ConferenceManager conferenceManager){
          this.usermanager = userManager;
          this.eventManager = eventManager;
          this.messageManager = messageManager;
          this.roomManager = roomManager;
          this.conferenceManager = conferenceManager;
          presenter = new UserPresenter();
          menuPresenter = new MenuPresenter();
          this.groupChatManager = groupChatManager;
          this.eventController = new EventController(eventManager, usermanager, roomManager,conferenceManager);
          this.messageController = new MessageController(usermanager, messageManager, eventManager);
          this.roomController = new RoomController(roomManager);
          this.conferenceController = new ConferenceController(roomController, conferenceManager);
          this.groupChatController = new GroupChatController(usermanager, groupChatManager,
                  messageManager);
          this.name = name;
          makeNewAccount();
          myConference = conferenceController.conferenceMenu();
          mainMenu();
     }

     public String getMyConference(){return myConference;}

     public ConferenceManager getConferenceManager() {
          return conferenceManager;
     }

     public ConferenceController getConferenceController() {
          return conferenceController;
     }

     /**
      * Gets the userManager
      * @return UserManager
      */
     public UserManager getUsermanager() {
          return usermanager;
     }

     /**
      * Gets the presenter
      * @return Presenter
      */
     public UserPresenter getPresenter() {
          return presenter;
     }

     /**
      * Gets the menu presenter
      * @return menuPresenter
      */
     public MenuPresenter getMenuPresenter() {
          return menuPresenter;
     }

     /**
      * Gets the user's name
      * @return String
      */
     public String getMyName() {
          return name;
     }

     /**
      * Changes the user's username
      * @param name The new name
      */
     public void setMyName(String name){
          this.name = name;
     }

     /**
      * Gets the eventController
      * @return EventController
      */
     public EventController getEventController(){
          return eventController;
     }

     /**
      * Gets the eventManager
      * @return EventManager
      */
     public EventManager getEventManager() {
          return eventManager;
     }

     public GroupChatManager getGroupChatManager(){ return groupChatManager;}

     /**
      * Gets the messageController
      * @return MessageController
      */
     public MessageController getMessageController() {
          return messageController;
     }

     /**
      * Gets the messageManager
      * @return MessageManager
      */
     public MessageManager getMessageManager() {
          return messageManager;
     }

     /**
      * Gets the roomController
      * @return RoomController
      */
     public RoomController getRoomController() {
          return roomController;
     }

     /**
      * Gets the roomManager
      * @return RoomManager
      */
     public RoomManager getRoomManager() {
          return roomManager;
     }

     /**
      * Gets the groupChatController
      * @return the groupChatController
      */
     public GroupChatController getGroupChatController(){ return groupChatController;}

     /**
      * Signs the user up for the event of their choosing if they are able to.
      */
     public void signUp(){
          if (usermanager.getUser(this.name).getHasCovid()){
               menuPresenter.printYouHaveCovid();
          }
          else{
               String eventName = getPresenter().getEventName(this.eventController.getEventByConference(myConference));

               if (!eventName.equals("0") && eventManager.containsEvent(eventName)){
                    if (usermanager.canAfford(name, eventManager.getEvent(eventName).getPrice()))
                    {
                         if (getEventController().addAttendee(eventName, getMyName())){
                              getPresenter().printSignedUp(eventName);
                              getPresenter().printCurrentBalance(usermanager.getUser(this.name).getBalance());
                              eventSignups += 1;
                         }
                         else{
                              getPresenter().printNotSignedUp(eventName);
                              signUp();
                         }
                    }
                    else{
                         getPresenter().printCantAfford();
                         getPresenter().printCurrentBalance(usermanager.getUser(this.name).getBalance());
                    }
               }
               else if (!eventManager.containsEvent(eventName)){
                    presenter.printInvalidOption();
                    signUp();
               }
          }

     }

     /**
      * Returns a list of events the user is signed up for
      * @return List<String> of the users events they are signed up for
      */
     public List<String> getMyEvents () {
          return usermanager.getUser(getMyName()).getEvents();
     }

     /**
      * Removes an event of the user's choosing from their list of attending events.
      */
     public void removeMyEvent(){
          getPresenter().printAttendeeEvents(getMyEvents());
          if (getMyEvents().size() > 0) {
               String event = getPresenter().printDeleteEvent();
               if (usermanager.cancelMyEvent(this.name, event, eventManager.getEvent(event).getPrice()) && eventManager.removeAttendee(event, this.name)) {
                    presenter.printRemovedEvent();
                    presenter.printCurrentBalance(usermanager.getUser(getMyName()).getBalance());
                    eventSignups = eventSignups - 1;
               } else {
                    presenter.printCantRemove();
               }
          }
     }

     /**
      * Adds the inputted event to the list of the user's event
      * @param likeEvent If true adds the event name in the list of names of liked events, otherwise removes the event
      *                  from the list of liked events if exists
      */
     public void likeUnlikeEvent(boolean likeEvent){

          if (usermanager.getUser(this.name).getHasCovid()){
               presenter.printYouHaveCovid();
          }
          else{
               String eventName = getPresenter().getEventName(usermanager.getUser(this.name).getEvents());
               List<String> myLikedEvents = usermanager.getUser(this.name).getLikedEvents();
               if (!eventName.equals("0")) {
                    int eventIndexInLiked = myLikedEvents.indexOf(eventName);

                    if (likeEvent && eventIndexInLiked < 0) myLikedEvents.add(eventName);
                    else if (!likeEvent && eventIndexInLiked >= 0) myLikedEvents.remove(eventIndexInLiked);
               }
          }
          mainMenu();
     }

     /**
      *
      * @return
      */

     private List<String> createColumnHeader() {
          List<String> columns = new ArrayList<>();
          columns.add("Day");
          columns.add("Duration");
          columns.add("Event Name");
          columns.add("Liked");
          return columns;
     }

     /** Returns true if the users event schedule has been exported successfully, otherwise returns false
      * @return true iff schedule has been exported successfully, otherwise returns false
      */
     public boolean exportMyEvents () {
          List<String> myEvents = this.getMyEvents();

          if (myEvents.size() <= 0) return false;

          ExportHTML export = new ExportHTML();
          String currentTime = java.time.LocalDate.now().toString();

          List<String> likedEvents = usermanager.getUser(this.name).getLikedEvents();
          List<String> columns = createColumnHeader();
          List<List<String>> rows = new ArrayList<>();
          for (String eventName : myEvents) {
               Event event = eventManager.getEvent(eventName);

               List<String> line = new ArrayList<>();
               line.add(event.getTime().toString());
               line.add(Integer.toString(event.getDuration()));
               line.add(eventName);
               line.add(Boolean.toString(likedEvents.contains(eventName)));

               rows.add(line);
          }

          return export.exportHTML(this.name + "_schedule_" + currentTime, columns, rows);
     }

     public void covidQuestions(){
          boolean positive = menuPresenter.printCovidQuestions();
          if (positive && !usermanager.getUser(this.name).getHasCovid()){
               usermanager.changeCovid(this.name, true);
               messageManager.messagePeople(usermanager.getAttendeeObjects(), "COVID ALERT", this.name+" has COVID-19. watch out!");
          }
          else if (!positive && usermanager.getUser(this.name).getHasCovid()){
               usermanager.changeCovid(this.name, false);
          }

     }

     /**
      * Adds money to the user's balance
      */
     public void addToBalance(){
          double amount = getPresenter().printAddToBalance();
          usermanager.getUser(getMyName()).addToBalance(amount);
          getPresenter().printCurrentBalance(usermanager.getUser(getMyName()).getBalance());
     }

     /**
      * Returns the total number of times users have logged in
      * @return the total number of times users have logged in
      */
     public static int getUsersLoggedIn(){
          return usersLoggedIn;
     }

     /**
      * Return the total number of times users have signed up for events.
      * This number will go down if a user removes themselves from an event
      */
     public static int getEventSignups(){
          return eventSignups;
     }


     /**
      * Creates a new account
      */
     abstract void makeNewAccount();

     /**
      *  Displays the actions available for the user through the presenter
      */
     abstract void mainMenu();

     /**
      * Logs user into their account
      */
     abstract void loginExistingAccount();




}
