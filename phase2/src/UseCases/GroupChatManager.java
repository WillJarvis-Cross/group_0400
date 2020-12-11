package UseCases;

import Entities.GroupChat;
import Entities.Message;
import Entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * This Use Case manages Group Chat. It can create new group chats, delete members and more
 * @author group_0400
 */
public class GroupChatManager implements Serializable {

    private Hashtable<String, GroupChat> groupChats; // A hashtable of all the groupchats where the key is the group chat name
    private MessageManager messageManager; // The MessageManager

    /**
     * Initializes a new GroupChatManager with the given parameters
     * @param messageManager The MessageManager
     */
    public GroupChatManager(MessageManager messageManager){
        groupChats = new Hashtable<>();
        this.messageManager = messageManager;
    }

    /**
     * Creates a new group chat with the given parameters
     * @param groupMembers The members of the group chat
     * @param groupName The name of the group chat
     */
    public void newGroupChat(List<User> groupMembers, String groupName){
        List<String> memberNames = new ArrayList<>();
        for (User member: groupMembers){
            member.addGroupChat(groupName);
            memberNames.add(member.getUsername());
        }
        groupChats.put(groupName, new GroupChat(memberNames));
    }

    /**
     * Checks if the given group chat name is unique or not
     * @param name The name of the group chat
     * @return True if the name is unique and false otherwise
     */
    public boolean uniqueGroupChat(String name){
        return !groupChats.containsKey(name);
    }

    /**
     * returns the groupchat entity corresponding to the name of the group chat
     * @param group The name of the group chat
     * @return The group chat corresponding to group
     */
    public GroupChat getGroupChat(String group){ return groupChats.get(group);}

    /**
     * Returns a list of all the messages of the form: "name: content"
     * @param group The groupchat we are looking at
     * @return A list of the group chats messages
     */
    public List<String> getGroupChatMessages(String group){
        List<String> messages = new ArrayList<>();
        for (int messageId: getGroupChat(group).getAllMessages()){
            Message thisMessage = messageManager.getMessage(messageId);
            messages.add(thisMessage.getSender()+": "+thisMessage.getContent());
        }
        return messages;
    }

    /**
     * Deletes the given member from the given group chat
     * @param person The person being removed
     * @param group The group in question
     */
    public void deleteMember(User person, String group){
        person.removeGroupChat(group);
        getGroupChat(group).delMember(person.getUsername());
    }
}
