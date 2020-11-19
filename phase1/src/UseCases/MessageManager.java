package UseCases;
import Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;
/** Represents the use case for Message objects
 * @author group 400
 */

public class MessageManager implements Serializable {
    private ArrayList<Message> messageList = new ArrayList<>();

    /**
     * Creates a Message with the given sender, receiver and content
     * @param sender Sender of the message
     * @param receiver Receiver of the message
     * @param content Content of the message
     */
    public void sendMessage(User sender, User receiver, String content){
        Message message = new Message(content, sender.getUsername(), receiver.getUsername());
        messageList.add(message);
        receiver.addMessage(message.getMessageId());

    }
    /**
     * Returns a message object with the given id
     * @param id id of the message
     * @return Message with the ID 'id'
     */
    public Message getMessage(int id){
        return messageList.get(id);
    }

    /**
     * Returns a list containing the Messages with the IDs stored in messages
     * @param messages an ArrayList of message IDs
     * @return an ArrayList of Messages
     */
    public ArrayList<Message> getMyMessages(List<Integer> messages){
        ArrayList<Message> newList = new ArrayList<>();
        for (int i: messages){
            if (i < messageList.size()){
                newList.add(messageList.get(i));
            }
        }
        return newList;
    }
}