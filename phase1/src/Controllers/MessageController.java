package Controllers;

import Entities.Message;
import UseCases.MessageManager;
import UseCases.UserManager;

import java.util.ArrayList;
import java.util.List;

public class MessageController {
    MessageManager messageManager;
    UserManager userManager;
    public MessageController(UserManager userManager){
        messageManager = new MessageManager();
        this.userManager = userManager;
    }

    public StringBuilder printMyMessages(String name){
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
        return output;
    }
    public boolean sendMessage(String sender, String receiver, String content){
        if (userManager.getUsers().containsKey(receiver)){
            messageManager.sendMessage(userManager.getUser(sender), userManager.getUser(receiver), content);
            return true;
        }
        return false;
    }

}
