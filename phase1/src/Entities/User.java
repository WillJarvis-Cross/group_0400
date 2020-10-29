import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public abstract class User{
    private List<Message> MessageInbox;
    private List<User> FriendList;
    private String password, name;
    //public static int ID=0;


    public User(String passwordInput, String nameInput){
        this.name = nameInput;
        this.password = passwordInput;
        this.FriendList = new ArrayList();
        this.MessageInbox = new ArrayList();
    }
    public List<Message> getMessageInbox() {
        return MessageInbox;
    }
    public List<User> getFriendList() {
        return FriendList;
    }
}
