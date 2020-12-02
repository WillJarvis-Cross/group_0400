package Entities;

import java.util.ArrayList;
import java.util.List;

public class GroupChat {
    private List<String> members; // The members of the group chat
    private List<Integer> allMessages; // The messages in the group chat
    private String groupName; // The name of the group chat

    public GroupChat(List<String> members, String groupName){
        this.members = members;
        this.allMessages = new ArrayList<>();
        this.groupName = groupName;
    }

    public List<String> getMembers(){ return members;}

    public List<Integer> getAllMessages(){ return allMessages;}

    public void newMessage(int id){
        allMessages.add(id);
    }

    public String getGroupName(){return groupName;}

    public boolean delMember(String name){
        if (members.contains(name)){
            members.remove(name);
            return true;
        }
        return false;
    }
}
