package Controllers;

import Presenter.Presenter;
import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.util.List;

public abstract class UserController {

     private final UserManager usermanager;
     private final Presenter presenter;
     private final EventController eventController;
     private final MessageController messageController;
     private final EventManager eventManager;
     private final MessageManager messageManager;
     private final RoomManager roomManager;
     private final RoomController roomController;
     private final String name;

     public UserController(String name){
          presenter = new Presenter();
          usermanager = new UserManager();
          eventManager = new EventManager();
          roomManager = new RoomManager();
          messageManager = new MessageManager();
          eventController = new EventController(this, presenter, eventManager, usermanager, roomManager);
          messageController = new MessageController(usermanager, this, presenter, messageManager);
          roomController = new RoomController(this, presenter, eventManager, roomManager);
          this.name = name;
          makeNewAccount();
     }

     //This one is for loading saved info
     public UserController(String name, UserManager userManager, EventManager eventManager, MessageManager messageManager, RoomManager roomManager){
          this.usermanager = userManager;
          this.eventManager = eventManager;
          this.messageManager = messageManager;
          this.roomManager = roomManager;
          this.presenter = new Presenter();
          this.eventController = new EventController(this, presenter, eventManager, usermanager, roomManager);
          this.messageController = new MessageController(usermanager, this, presenter, messageManager);
          this.roomController = new RoomController(this, presenter, eventManager, roomManager);
          this.name = name;
     }

     public UserManager getUsermanager() {
          return usermanager;
     }

     public Presenter getPresenter() {
          return presenter;
     }

     public String getMyName() {
          return name;
     }

     public EventController getEventController(){
          return eventController;
     }

     public EventManager getEventManager() {
          return eventManager;
     }

     public MessageController getMessageController() {
          return messageController;
     }
     public RoomController getRoomController() {
          return roomController;
     }

     public void loginExistingAccount(){
          while (true){
               String password = presenter.printPassword();
               if (password.equals("0")){
                    makeNewAccount();
               }
               else{
                    if (usermanager.login(name, password)){
                         break;
                    }
                    else{
                         presenter.printInvalidInput();
                    }
               }
          }
          mainMenu();
     }

     public void signUp(){
          String eventName = getPresenter().getEventName();
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

     public List<String> getMyEvents () {
          return usermanager.getUser(getMyName()).getEvents();
     }

     public void removeMyEvent(){
          getPresenter().printAttendeeEvents(getMyEvents());
          String event = getPresenter().printDeleteEvent();
          if (usermanager.cancelMyEvent(this.name, event) && eventManager.removeAttendee(this.name, event)){
               presenter.printRemovedEvent();
          }
          else{
               presenter.printCantRemove();
          }
          mainMenu();
     }

     abstract void makeNewAccount();

     abstract void mainMenu();
}
