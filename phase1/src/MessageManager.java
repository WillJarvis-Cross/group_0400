package Entities;
import java.util.String;


public class MessageManager{

    public static void sendMessage(User sender, User reciever, String content){
        Message message = new Message(content, sender, reciever);
        //reciever.addMessage(message);
        //The line above will be added after inconsistencies between user classes are sorted out
    }


}