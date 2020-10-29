package Entities;

/*
 * A message between two users contains the content of the
 * message as well as the sender and the receiver of the message
 */
public class Message {
    // This message's content
    private final String content;

    // The user who sent the message
    private final String senderUsername;

    // The user receiving the message
    private final String receiverUsername;

    public Message(String content, String senderUsername, String receiverUsername){
        this.content = content;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    // Return the content of the message
    public String getContent(){
        return content;
    }
    //return the username of the User who sent the message
    public String getSender(){
        return senderUsername;
    }
    // Return the username of the User who received the message
    public String getReceiver(){
        return receiverUsername;
    }
}
