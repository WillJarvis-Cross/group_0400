package Controllers;

import Presenter.Presenter;
import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.io.Serializable;
import java.util.List;

public abstract class UserController implements Serializable {

     private final UserManager usermanager;
     private transient final Presenter presenter;
     private transient final EventController eventController;
     private transient final MessageController messageController;
     private final EventManager eventManager;
     private final MessageManager messageManager;
     private final RoomManager roomManager;
     private transient final RoomController roomController;
     private String name;

     /**
      * Creates an instance of UserController with the given name
      * Also initializes the other controllers and use cases
      * When its done initializing it calls makeNewAccount which is the login/make new account screen for the user
      * @param name Name of the user
      */
     public UserController(String name){
          presenter = new Presenter();
          usermanager = new UserManager();
          eventManager = new EventManager();
          roomManager = new RoomManager();
          messageManager = new MessageManager();
          eventController = new EventController(this, presenter, eventManager, usermanager, roomManager);
          messageController = new MessageController(usermanager, this, presenter, messageManager, eventManager);
          roomController = new RoomController(this, presenter, roomManager);
          this.name = name;
          makeNewAccount();
     }

     /**
      * This constructor is used for when we are loading a file
      * @param name Name of the User
      * @param userManager The UserManager to be stored
      * @param eventManager The EventManager to be stored
      * @param messageManager The MessageManager to be stored
      * @param roomManager The RoomManager to be stored
      */
     public UserController(String name, UserManager userManager, EventManager eventManager, MessageManager messageManager, RoomManager roomManager){
          this.usermanager = userManager;
          this.eventManager = eventManager;
          this.messageManager = messageManager;
          this.roomManager = roomManager;
          this.presenter = new Presenter();
          this.eventController = new EventController(this, presenter, eventManager, usermanager, roomManager);
          this.messageController = new MessageController(usermanager, this, presenter, messageManager, eventManager);
          this.roomController = new RoomController(this, presenter, roomManager);
          this.name = name;
          makeNewAccount();
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
     public Presenter getPresenter() {
          return presenter;
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
      * Signs the user up for the event of their choosing if they are able to.
      */
     public void signUp(){
          String eventName = getPresenter().getEventName(eventManager.getAllEventsString());
          if (eventName.equals("0")){
               mainMenu();
          }
          else{
               if (getEventController().addAttendee(eventName, getMyName())){
                    getPresenter().printSignedUp(eventName);
                    mainMenu();
               }
               else{
                    getPresenter().printNotSignedUp(eventName);
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
               if (usermanager.cancelMyEvent(this.name, event) && eventManager.removeAttendee(event, this.name)) {
                    presenter.printRemovedEvent();
               } else {
                    presenter.printCantRemove();
               }
          }
          mainMenu();
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
