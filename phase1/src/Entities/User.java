package Entities;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public abstract class User{
    // List of messages sent to this user
    private List<Message> messageInbox;

    // Holds a list of the user's friends whom the user can message
    private List<User> friendList;

    // The user's username and password which is used to log in
    private String password, username;


    public User(String passwordInput, String nameInput){
        this.username = nameInput;
        this.password = passwordInput;
        this.friendList = new ArrayList();
        this.messageInbox = new ArrayList();
    }

    // Returns the user's list of messages
    public List<Message> getMessageInbox() {
        return messageInbox;
    }

    // Adding a new message to the user's list of messages
    public void addMessage(Message newMessage){ messageInbox.add(newMessage);}

    // Returns the list of the user's friends
    public List<User> getFriendList() {
        return friendList;
    }

    // Adds a User to the list of friends
    public void addFriend(User friend){ friendList.add(friend);}

    //Returns the user's password
    public String getPassword(){ return password;}

    // Changes the user's password
    public void setPassword(String newPassword){ password = newPassword;}

    // Returns the user's username
    public String getUsername(){ return username;}

    // Changes the user's username
    public void setUsername(String newUsername){ username = newUsername;}

}
