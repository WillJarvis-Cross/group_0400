package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Attendee extends User{

    private List<Message> messageInbox;
    private List<User> friendList;
    private String password, name;

    public Attendee(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();
    }

    public boolean isOrganizer(){ return false;}
}
