import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class Attendee extends User{
    private List<Message> MessageInbox;
    private List<User> FriendList;
    private String password, name;
    //public static int ID=0;



    public Attendee(String passwordInput, String nameInput){
            super(passwordInput, nameInput);
    }
}
