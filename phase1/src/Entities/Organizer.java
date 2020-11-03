package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Organizer extends User{
    private List<Message> messageInbox;
    private List<User> friendList;

    public Organizer(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();
    }

    @Override
    boolean isOrganizer() { return true;}

    @Override
    boolean isSpeaker() { return false;}
}
