import java.lang.String;
public class Message {
    private String messages;
    private User sender, receiver;

    public Message(String messages, User sender, User receiver) {
        this.messages = messages;
        this.sender = sender;
        this.receiver = receiver;
    }
}
