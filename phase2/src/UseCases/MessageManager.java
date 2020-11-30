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

    private List<Message> messageList = new ArrayList<>(); // arraylist of all the messages

    /**
     * Creates a Message with the given sender, receiver and content
     * @param sender Sender of the message
     * @param receiver Receiver of the message
     * @param content Content of the message
     */
    public void sendMessage(User sender, User receiver, String content){
        Message message = new Message(content, sender.getUsername(), receiver.getUsername(), messageList.size());
        receiver.addMessage(messageList.size());
        messageList.add(message);
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
    public List<Message> getMyMessages(List<Integer> messages){
        List<Message> newList = new ArrayList<>();
        for (int i: messages){
            if (i < messageList.size()){
                newList.add(messageList.get(i));
            }
        }
        return newList;
    }

    /**
     * Marks the given message as unread if it not already marked as unread
     * @param id The id of the message being marked as unread
     * @return True if the message was marked as unread
     */
    public boolean markUnread(int id){
        Message message = getMessage(id);
        if (message.getUnread()){
            return false;
        }
        message.setUnread(true);
        return true;
    }

    public boolean delMessage(User person, int id){
        return person.deleteMessage(id);
    }

    public boolean archiveMessage(User person, int id){
        if (delMessage(person, id)){
            person.addArchivedMessage(id);
            return true;
        }
        return false;
    }

    public boolean unArchiveMessage(User person, int id){
        if (person.delArchivedMessage(id)){
            person.addMessage(id);
            return true;
        }
        return false;
    }
}