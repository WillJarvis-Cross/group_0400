package Entities;
import java.io.Serializable;
import java.lang.String;
import java.util.List;

/** This is an entity for a Message which contains the content of the message, the name of the users who
 * sent and received the message, and the id of the message
 * @author group 0400
 */
public class Message implements Serializable {

    private final String content; // This message's content
    private final String sender; // The user who sent the message
    private final String receiver; // The user receiving the message
    private final Integer messageId; // The ID of the message

    /**
     * Constructs a new message with content, sender, and receiver which is inputted. The message id is given a value
     * based on how many messages have been created so far.
     * @param content The content of the message
     * @param sender The name of the user who sent the message
     * @param receiver The name of the user who received the message
     */
    public Message(String content, String sender, String receiver, int numMessages){
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
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
     * Returns the name of the user who received the message
     * @return the name of the user who received the message
     */
    public String getReceiver(){
        return receiver;
    }

    /**
     * Returns the messageId
     * @return the messageId
     */
    public int getMessageId(){ return messageId;}

    public void printAttendeeEvents(List<String> events) {
        if (events.isEmpty()) {
            System.out.println("You are not signed up for any events");
        }
        else {
            System.out.println("Here is a list of events you are signed up for");
            for (String name: events) {
                System.out.println(name);
            }
        }
    }
}

