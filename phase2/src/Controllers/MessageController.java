package Controllers;

import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.UserManager;
import Presenter.*;

import java.util.List;
import java.util.Set;

/** Represents the controller for Message
 * @author group 400
 */
public class MessageController {
    private final MessageManager messageManager;
    private final UserManager userManager;
    private final UserController userController;
    private final MessagePresenter presenter;
    private final EventManager eventManager;

    /**
     * Creates a new MessageController with the already created managers
     * @param userManager The userManager
     * @param userController The UserController
     * @param messageManager The MessageManager
     */
    public MessageController(UserManager userManager, UserController userController,
                             MessageManager messageManager, EventManager eventManager){
        this.messageManager = messageManager;
        this.userManager = userManager;
        this.userController = userController;
        this.presenter = new MessagePresenter();
        this.eventManager = eventManager;
    }


    /**
     * Present all message send to user
     * Uses Presenter class to output the message to user
     *
     * @param name The name of the person whose messages are being printed
     */
    public void printMyMessages(String name){
        StringBuilder output = new StringBuilder("My Messages:\n");
        List<Integer> messagesIds = userManager.getUser(name).getMessageInbox();

        // I do this loop counting backwards so it prints the messages based on which was most recent
        for (int i = messagesIds.size() - 1; i >= 0; i--){
            output.append(messageManager.getMyMessages(messagesIds).get(i).getSender());
            output.append(": ");
            output.append(messageManager.getMyMessages(messagesIds).get(i).getContent());
            output.append("\n");
        }
        String input = presenter.printReceivedMessages(output);

        userController.mainMenu();
    }

    /**
     * Send message to User using message manager
     * <p>
     *     message is receive as content
     *     sender calls userManger to send message to sender
     * </p>
     *
     * @param sender The person sending the message
     */
    public void sendMessage(String sender){
        String receiver;
        Boolean send = true;
        while (true){
            receiver = presenter.printWhoToSendTo();
            if (userManager.getUsers().containsKey(receiver)){
                break;
            }
            else if (receiver.equals("0")){
                userController.mainMenu();
                send = false;
                break;
            }
            else{
                presenter.printNoName();
            }
        }
        if (send) {
            String content = presenter.printMessage();
            messageManager.sendMessage(userManager.getUser(sender), userManager.getUser(receiver), content);
            presenter.printMessageSent();
            userController.mainMenu();
        }

    }

    /**
     * The organizer with the given name sends a message to all speakers.
     * The contents of thw message will be obtained through the presenter.
     * @param name of the organizer who is sending the message
     */
    public void messageAllSpeakers(String name){
        String content = presenter.printMessage();
        Set<String> keys = userManager.getSpeakers().keySet();
        for (String key : keys) {
            messageManager.sendMessage(userManager.getOrganizer(name),
                    userManager.getSpeaker(key), content);
        }
        presenter.printMessageSent();
        userController.mainMenu();
    }

    /**
     * The organizer with the given name sends a message to all attendees.
     * The contents of thw message will be obtained through the presenter.
     * @param name of the organizer who is sending the message
     */
    public void messageAllAttendees(String name){
        String content = presenter.printMessage();
        Set<String> keys = userManager.getAttendees().keySet();
        for (String key : keys) {
            messageManager.sendMessage(userManager.getOrganizer(name),
                    userManager.getAttendee(key), content);
        }
        presenter.printMessageSent();
        userController.mainMenu();
    }

    /**
     * Sends a message to every attendee at a given event
     * @param name The speaker sending the message
     */
    public void messageAllAttendeesAtEvent(String name){
        presenter.printAttendeeEvents(userManager.getUser(name).getEvents());
        String eventName = presenter.printMessageEvent();
        if (!eventManager.containsEvent(eventName)){
            presenter.printInvalidOption();
        }
        else {
            String content = presenter.printMessage();
            List<String> allPeople = eventManager.getEvent(eventName).getAttending();
            if (allPeople.size() == 0) {
                presenter.printEmptyEvent();
            } else {
                for (String person : allPeople) {
                    messageManager.sendMessage(userManager.getUser(name), userManager.getUser(person), content);
                }
                presenter.printMessageSent();
            }
        }
        userController.mainMenu();
    }
}
