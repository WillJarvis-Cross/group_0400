package Controllers;

import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.UserManager;
import Presenter.MessagePresenter;

import java.util.List;

/** Represents the controller for Message
 * @author group 400
 */
public class MessageController {
    private final MessageManager messageManager;
    private final UserManager userManager;
    private final MessagePresenter presenter;
    private final EventManager eventManager;

    /**
     * Creates a new MessageController with the already created managers
     * @param userManager The userManager
     * @param messageManager The MessageManager
     */
    public MessageController(UserManager userManager,
                             MessageManager messageManager, EventManager eventManager){
        this.messageManager = messageManager;
        this.userManager = userManager;
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

        helpPrintMessages(messagesIds, output);

        if (!output.toString().equals("My Messages:\n")){
            int input = presenter.printReceivedMessages(output);
            if (input > 0 && input <= messagesIds.size()){
                advancedMessageOptions(messagesIds, input, name);
            }
        }
        else{
            presenter.printNoMessages();
        }
    }

    /**
     * This is used to deal with the advanced message options("Mark as unread", delete, archive)
     * @param messageIds The list of the user's messages
     * @param input The message they are looking at using advanced options on
     * @param name The name of the user
     */
    private void advancedMessageOptions(List<Integer> messageIds, int input, String name){
        String sender = messageManager.getMyMessages(messageIds).get(input - 1).getSender();
        String content = messageManager.getMyMessages(messageIds).get(input - 1).getContent();
        int id = messageManager.getMyMessages(messageIds).get(input - 1).getMessageId();
        int option = presenter.printMessageOption(sender, content, messageManager.unreadStatus(id));

        if (option == 0){
            printMyMessages(name);
        }
        else if (option == 1){
            messageManager.markUnread(id);
        }
        else if (option == 2){
            presenter.printDeleted(messageManager.delMessage(userManager.getUser(name), id));
        }
        else{
            presenter.printArchived(messageManager.archiveMessage(userManager.getUser(name), id));
        }
    }

    /**
     * This shows the users archived messages and also allows them to unarchive messages
     * @param name The name of the user
     */
    public void seeArchivedMessages(String name){
        StringBuilder output = new StringBuilder("My Archived Messages:\n");
        List<Integer> messagesIds = userManager.getUser(name).getArchivedMessages();

        helpPrintMessages(messagesIds, output);
        if (!output.toString().equals("My Archived Messages:\n")){
            int input = presenter.printReceivedMessages(output);
            if (input != 0){
                if (presenter.printUnarchive()){
                    int id = messageManager.getMyMessages(messagesIds).get(input - 1).getMessageId();
                    messageManager.unArchiveMessage(userManager.getUser(name), id);
                }
            }
        }
        else{
            presenter.printNoMessages();
        }
    }

    private void helpPrintMessages(List<Integer> messageIds, StringBuilder output){
        // I do this loop counting backwards so it prints the messages based on which was most recent
        for (int i = messageIds.size() - 1; i >= 0; i--){
            if (messageManager.getMyMessages(messageIds).get(i).getUnread()){
                output.append("(Unread) ");
            }
            else{
                output.append("(Read)   ");
            }
            output.append(messageIds.size()-i);
            output.append(": ");
            output.append(messageManager.getMyMessages(messageIds).get(i).getSender());
            output.append(": ");
            output.append(messageManager.getMyMessages(messageIds).get(i).getContent());
            output.append("\n");
        }
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
        boolean send = true;
        while (true){
            receiver = presenter.printWhoToSendTo();
            if (userManager.getUsers().containsKey(receiver)){
                break;
            }
            else if (receiver.equals("0")){
                send = false;
                break;
            }
            else{
                presenter.printNoName();
            }
        }
        if (send) {
            String content = presenter.printMessage();
            messageManager.sendMessage(sender, userManager.getUser(receiver), content);
            presenter.printMessageSent();
        }

    }

    /**
     * The organizer with the given name sends a message to all speakers.
     * The contents of thw message will be obtained through the presenter.
     * @param name of the organizer who is sending the message
     */
    public void messageAllSpeakers(String name){
        String content = presenter.printMessage();
        messageManager.messagePeople(userManager.getSpeakerObjects(), name, content);
        presenter.printMessageSent();
    }

    /**
     * The organizer with the given name sends a message to all attendees.
     * The contents of thw message will be obtained through the presenter.
     * @param name of the organizer who is sending the message
     */
    public void messageAllAttendees(String name){
        String content = presenter.printMessage();
        messageManager.messagePeople(userManager.getAttendeeObjects(), name, content);
        presenter.printMessageSent();
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
                    messageManager.sendMessage(name, userManager.getUser(person), content);
                }
                presenter.printMessageSent();
            }
        }
    }
}
