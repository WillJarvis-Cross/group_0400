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
    public void sendMessage(String sender, User receiver, String content){
        Message message = new Message(content, sender, messageList.size());
        receiver.addMessage(messageList.size());
        messageList.add(message);
    }

    public void groupMessage(String sender, GroupChat group, String content){
        Message message = new Message(content, sender, messageList.size());
        group.newMessage(messageList.size());
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
     * Marks the given message as unread or read depending on its current status.
     * It will mark it as unread if it was previously read
     * @param id The id of the message being altered
     */
    public void markUnread(int id){
        getMessage(id).setUnread(!unreadStatus(id));
    }

    /**
     * Returns true when the message is unread and false when it is read
     * @param id The id of the message in question
     * @return true when the message is unread and false when it is read
     */
    public boolean unreadStatus(int id){
        return getMessage(id).getUnread();
    }

    /**
     * Deletes the given message from the users inbox
     * @param person The user in which the message is being deleted from
     * @param id The id of the message in question
     * @return True if the message was deleted and false otherwise
     */
    public boolean delMessage(User person, int id){
        return person.deleteMessage(id);
    }

    /**
     * Changes a message from the message inbox to the archived messages
     * @param person The person who's message we are altering
     * @param id The id of the message in question
     * @return True if the message was archived and false otherwise
     */
    public boolean archiveMessage(User person, int id){
        if (delMessage(person, id)){
            person.addArchivedMessage(id);
            return true;
        }
        return false;
    }

    /**
     * Changes a message from archived to the message inbox
     * @param person The person who's message we are altering
     * @param id The id of the message in question
     * @return True if the message was unarchived and false otherwise
     */
    public boolean unArchiveMessage(User person, int id){
        if (person.delArchivedMessage(id)){
            person.addMessage(id);
            return true;
        }
        return false;
    }

    public void messagePeople(List<User> people, String sender, String content){
        for (User person: people){
            sendMessage(sender, person, content);
        }
    }

}