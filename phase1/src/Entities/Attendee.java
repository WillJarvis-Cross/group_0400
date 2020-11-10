package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Attendee extends User{
    // List of the messages sent to the attendee
    private List<Integer> messageInbox;

    // List of friends of the attendee
    private List<User> friendList;



    public Attendee(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();

    }

    @Override
    public boolean isOrganizer(){ return false;}

    @Override
    boolean isSpeaker() { return false;}
}
