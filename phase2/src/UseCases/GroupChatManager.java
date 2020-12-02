package UseCases;

import Entities.GroupChat;
import Entities.Message;
import Entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GroupChatManager implements Serializable {
    private Hashtable<String, GroupChat> groupChats;
    private MessageManager messageManager;

    public GroupChatManager(MessageManager messageManager){
        groupChats = new Hashtable<>();
        this.messageManager = messageManager;
    }

    public void newGroupChat(List<User> groupMembers, String groupName){
        List<String> memberNames = new ArrayList<>();
        for (User member: groupMembers){
            member.addGroupChat(groupName);
            memberNames.add(member.getUsername());
        }
        groupChats.put(groupName, new GroupChat(memberNames, groupName));
    }

    public boolean uniqueGroupChat(String name){
        return !groupChats.containsKey(name);
    }

    public List<String> getMyGroupChats(User person){
        return person.getGroupChats();
    }

    public GroupChat getGroupChat(String group){ return groupChats.get(group);}

    public List<String> getGroupChatMessages(String group){
        List<String> messages = new ArrayList<>();
        for (int messageId: getGroupChat(group).getAllMessages()){
            Message thisMessage = messageManager.getMessage(messageId);
            messages.add(thisMessage.getSender()+": "+thisMessage.getContent());
        }
        return messages;
    }

    public boolean deleteMember(User person, String group){
        person.removeGroupChat(group);
        return getGroupChat(group).delMember(person.getUsername());
    }
}
