package Controllers;

import Presenter.GroupChatPresenter;
import UseCases.GroupChatManager;
import UseCases.MessageManager;
import UseCases.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The controller which handles users inputs to do with group chats
 * @author group_0400
 */
public class GroupChatController {

    private final UserManager userManager; // The user manager
    private final GroupChatManager groupChatManager; // The group chat manager
    private final GroupChatPresenter presenter; // The group chat presenter
    private final MessageManager messageManager; // The message manager

    /**
     * Initializes the group chat controller with the given parameters
     * @param userManager the user manager
     * @param groupChatManager the group chat manager
     * @param messageManager the message manager
     */
    public GroupChatController(UserManager userManager, GroupChatManager groupChatManager, MessageManager messageManager){
        this.groupChatManager = groupChatManager;
        this.userManager = userManager;
        this.presenter = new GroupChatPresenter();
        this.messageManager = messageManager;
    }

    /**
     * Presents a list of the user's group chats
     * @param name The name of the person who's group chats we are printing
     */
    public void showGroupChats(String name){
        String group = presenter.printMyGroupChats(userManager.getMyGroupChats(name));
        if (group != null && !group.equals("0")){
            if (presenter.printGroupChatMessages(groupChatManager.getGroupChatMessages(group))){
                messageManager.groupMessage(name, groupChatManager.getGroupChat(group), presenter.printMessage());
                presenter.printMessageSent();
            }
            else{
                groupChatManager.deleteMember(userManager.getUser(name), group);
            }

        }
    }

    /**
     * Takes the user's input for who will be in the group chat and the name of the group chat and
     * creates a group chat with that info
     * @param name The name of the person creating the group chat
     */
    public void createGroupChat(String name){
        while (true){
            String[] names = presenter.printGroupChatNames();
            if (userManager.canMakeGroupChat(names)){
                String groupName = presenter.printGroupName();
                if (groupChatManager.uniqueGroupChat(groupName)){
                    List<String> allNames = new ArrayList<>();
                    allNames.addAll(Arrays.asList(names));
                    if (!allNames.contains(name)){
                        allNames.add(name);
                    }
                    groupChatManager.newGroupChat(userManager.getListOfUsers(allNames), groupName);
                    break;
                }
                else{
                    presenter.printInvalidOption();
                }
            }
            else{
                presenter.printInvalidOption();
            }
        }
    }
}
