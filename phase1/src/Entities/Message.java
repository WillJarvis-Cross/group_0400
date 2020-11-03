package Entities;
import java.lang.String;

public class Message {
    private static int numMessages = 0;

    // This message's content
    private final String content;

    // The user who sent the message
    private final String sender;

    // The user receiving the message
    private final String receiver;

    private final Integer messageId;

    public Message(String content, String sender, String receiver){
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        messageId = numMessages;
        numMessages ++;
    }

    // Return the content of the message
    public String getContent(){
        return content;
    }
    //return the User who sent the message
    public String getSender(){
        return sender;
    }
    // Return the User who received the message
    public String getReceiver(){
        return receiver;
    }
    // Return the messageId
    public int getMessageId(){ return messageId;}
}

