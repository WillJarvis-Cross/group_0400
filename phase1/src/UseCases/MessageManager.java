package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;
/** Represents the use case for Message objects
 * @author group 400
 */

public class MessageManager{
    private static ArrayList<Message> messageList = new ArrayList<Message>();

    /**
     * Creates a Message with the given sender, receiver and content
     * @param sender
     * @param receiver
     * @param content
     */
    public void sendMessage(User sender, User receiver, String content){
        Message message = new Message(content, sender.getUsername(), receiver.getUsername());
        messageList.add(message);
        receiver.addMessage(message.getMessageId());

    }
    /**
     * Returns a message object with the given id
     * @param id
     * @return Message
     */
    public Message getMessage(int id){
        return messageList.get(id);
    }

    /**
     * Returns a list containing the Messages with the IDs stored in messages
     * @param messages
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