package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.lang.String;



public class MessageManager{
    private static ArrayList<Message> messageList = new ArrayList<Message>();

    public static void sendMessage(User sender, User receiver, String content){
        Message message = new Message(content, sender.getUsername(), receiver.getUsername());
        messageList.add(message);
        receiver.addMessage(message.getMessageId());

    }

    public static Message getMessage(int id){
        return messageList.get(id);
    }


}