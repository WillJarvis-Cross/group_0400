package Controllers;

import Entities.Event;
import Presenter.Presenter;
import UseCases.EventManager;
import UseCases.UserManager;

import java.util.ArrayList;

public abstract class UserController {

     private final UserManager usermanager;
     private final Presenter presenter;
     private final EventController eventController;
     private final MessageController messageController;
     private final EventManager eventManager;
     private final String name;

     public UserController(String name){
          presenter = new Presenter();
          usermanager = new UserManager();
          eventController = new EventController(this, presenter);
          eventManager = eventController.geteManager();
          messageController = new MessageController(usermanager, this, presenter);
          this.name = name;
          makeNewAccount();
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

     public void loginExistingAccount(){
          while (true){
               String password = presenter.printPassword();
               if (usermanager.login(name, password)){
                    break;
               }
               else{
                    presenter.printInvalidInput();
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

     public ArrayList<String> getMyEvents () {
          ArrayList<String> newList = new ArrayList<>();
          for (Event event : getEventManager().getEventsByUsername(getMyName())){
               newList.add(event.getEventName());
          }
          return newList;
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
