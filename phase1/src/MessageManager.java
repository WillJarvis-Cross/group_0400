import Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;



public class MessageManager{
    private List<Message> messageList = new ArrayList()

    public static void sendMessage(User sender, User reciever, String content){
        Message message = new Message(content, sender, reciever);
        messageList.add(message);
        reciever.addMessage(message.getMessageId());

    }


}