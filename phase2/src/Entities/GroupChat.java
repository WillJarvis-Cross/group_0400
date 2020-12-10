package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an entity for a group chat. It holds all the members of the group chat, the messages, and
 * the name og the group
 * @author group_0400
 */
public class GroupChat {
    private List<String> members; // The members of the group chat
    private List<Integer> allMessages; // The messages in the group chat
    private final String groupName; // The name of the group chat

    /**
     * Constructs a group chat with the given parameters
     * @param members The members of the group chat
     * @param groupName The name og the group chat
     */
    public GroupChat(List<String> members, String groupName){
        this.members = members;
        this.allMessages = new ArrayList<>();
        this.groupName = groupName;
    }

    /**
     * Returns the id of every message in the group chat
     * @return the id of every message in the group chat
     */
    public List<Integer> getAllMessages(){ return allMessages;}

    /**
     * Adding a new message to the list of messages in this group chat
     * @param id The id of the message being added
     */
    public void newMessage(int id){
        allMessages.add(id);
    }

    /**
     * Deletes the given person from the group chat if possible
     * @param name The person being deleted
     * @return True if the person was deleted and false otherwise
     */
    public boolean delMember(String name){
        if (members.contains(name)){
            members.remove(name);
            return true;
        }
        return false;
    }
}
