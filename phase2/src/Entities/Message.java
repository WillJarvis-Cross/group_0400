package Entities;
import java.io.Serializable;
import java.lang.String;
import java.util.List;

/** This is an entity for a Message which contains the content of the message, the name of the user who
 * sent the message, and the id of the message
 * @author group 0400
 */
public class Message implements Serializable {

    private final String content; // This message's content
    private final String sender; // The user who sent the message
    private final Integer messageId; // The ID of the message
    private boolean unread = true; // Whether the message has been marked as unread or not

    /**
     * Constructs a new message with content, sender, and receiver which is inputted. The message id is given a value
     * based on how many messages have been created so far.
     * @param content The content of the message
     * @param sender The name of the user who sent the message
     * @param numMessages The id of the message
     */
    public Message(String content, String sender, int numMessages){
        this.content = content;
        this.sender = sender;
        this.messageId = numMessages;
    }

    /**
     * Returns the content of this message
     * @return the content of this message
     */
    public String getContent(){
        return content;
    }

    /**
     * Returns the name of the user who sent the message
     * @return the name of the user who sent the message
     */
    public String getSender(){
        return sender;
    }

    /**
     * Returns the messageId
     * @return the messageId
     */
    public int getMessageId(){ return messageId;}

    /**
     * Returns true when the message has been marked as unread and false otherwise
     * @returntrue when the message has been marked as unread and false otherwise
     */
    public boolean getUnread(){ return unread; }

    /**
     * Sets the value of unread to the new status of the message
     * @param read The status of the new message
     */
    public void setUnread(boolean read){ unread = read;}
}

