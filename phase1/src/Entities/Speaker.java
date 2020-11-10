package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class Speaker extends User{
    private List<Integer> messageInbox;
    private List<User> friendList;

    public Speaker(String passwordInput, String nameInput){
        super(passwordInput, nameInput);
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();

    }

    @Override
    boolean isOrganizer() { return false;}

    @Override
    boolean isSpeaker() { return true;}
}
