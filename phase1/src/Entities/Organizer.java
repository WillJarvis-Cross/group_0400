package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Organizer extends User{
    private List<Message> MessageInbox;
    private List<User> FriendList;
    private String password, name;


    public Organizer(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
    }
}
