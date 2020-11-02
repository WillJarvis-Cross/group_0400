package Entities;
import java.lang.String;

public class Message {
    // This message's content
    private final String content;

    // The user who sent the message
    private final User sender;

    // The user receiving the message
    private final User receiver;

    public Message(String content, User sender, User receiver){
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    // Return the content of the message
    public String getContent(){
        return content;
    }
    //return the User who sent the message
    public User getSender(){
        return sender;
    }
    // Return the User who received the message
    public User getReceiver(){
        return receiver;
    }
}

