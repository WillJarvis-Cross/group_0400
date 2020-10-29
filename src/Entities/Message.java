package Entities;

/*
 * A message between two users contains the content of the
 * message as well as the sender and the receiver of the message
 */
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

    public String getContent(){
        return content;
    }
    public User getSender(){
        return sender;
    }
    public User getReceiver(){
        return receiver;
    }
}
