package Controllers;

import Entities.Message;
import UseCases.MessageManager;
import UseCases.UserManager;
import Presenter.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Represents the controller for Message
 * @author group 400
 */
public class MessageController {
    MessageManager messageManager;
    UserManager userManager;
    UserController userController;
    Presenter presenter;

    /**
     * initialize object for message controller
     * userManager is assigned
     *
     * @param userManager
     */
    public MessageController(UserManager userManager, UserController userController, Presenter presenter){
        messageManager = new MessageManager();
        this.userManager = userManager;
        this.userController = userController;
        this.presenter = presenter;
    }


    /**
     * Present all message send to user
     * Uses Presenter class to output the message to user
     *
     * @param name
     */
    public void printMyMessages(String name){
        StringBuilder output = new StringBuilder("My Messages:");
        List<Integer> messagesIds = userManager.getUser(name).getMessageInbox();
        if (messagesIds.size() == 0){
            presenter.printReceivedMessages(output);
            String input = presenter.printNoMessages();
        }
        else{
            ArrayList<Message> messages = messageManager.getMyMessages(messagesIds);
            // I do this loop counting backwards so it prints the messages based on which was most recent
            for (int i = messages.size() - 1; i >= 0; i--){
                output.append(messages.get(i).getSender());
                output.append(": ");
                output.append(messages.get(i).getContent());
                output.append("\n");
            }
            String input = presenter.printReceivedMessages(output);
        }
        userController.mainMenu();

    }

    /**
     * Send message to User using message manager
     * <p>
     *     message is receive as content
     *     sender calls userManger to send message to sender
     * </p>
     *
     * @param sender
     * @param receiver
     * @param content
     * @return
     */
    public void sendMessage(String sender){
        String receiver;
        while (true){
            receiver = Presenter.printWhoToSendTo();
            if (userManager.getUsers().containsKey(receiver)){
                break;
            }
            else if (receiver.equals("0")){
                userController.mainMenu();
            }
            else{
                presenter.printInvalidInput();
            }
        }
        String content = presenter.printMessage();
        messageManager.sendMessage(userManager.getUser(sender), userManager.getUser(receiver), content);
        presenter.printMessageSent();
        userController.mainMenu();
    }

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
}
