package Controllers;

import Entities.Message;
import UseCases.MessageManager;
import UseCases.UserManager;
import Presenter.Presenter;

import java.util.ArrayList;
import java.util.List;
/** Represents the controller for Message
 * @author group 400
 */
public class MessageController {
    MessageManager messageManager;
    UserManager userManager;

    /**
     * initialize object for message controller
     * userManager is assigned
     *
     * @param userManager
     */
    public MessageController(UserManager userManager){
        messageManager = new MessageManager();
        this.userManager = userManager;
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
        ArrayList<Message> messages = messageManager.getMyMessages(messagesIds);
        // I do this loop counting backwards so it prints the messages based on which was most recent
        for (int i = messages.size() - 1; i >= 0; i--){
            output.append(messages.get(i).getSender());
            output.append(": ");
            output.append(messages.get(i).getContent());
            output.append("\n");
        }
        Presenter.printReceivedMessages(output);
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
    public boolean sendMessage(String sender, String receiver, String content){
        if (userManager.getUsers().containsKey(receiver)){
            messageManager.sendMessage(userManager.getUser(sender), userManager.getUser(receiver), content);
            return true;
        }
        return false;
    }

}
