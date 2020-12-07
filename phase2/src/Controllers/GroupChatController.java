package Controllers;

import Presenter.GroupChatPresenter;
import UseCases.GroupChatManager;
import UseCases.MessageManager;
import UseCases.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupChatController {
    private final UserManager userManager;
    private final GroupChatManager groupChatManager;
    private final GroupChatPresenter presenter;
    private final UserController userController;
    private final MessageManager messageManager;

    public GroupChatController(UserManager userManager, UserController userController,
                             GroupChatManager groupChatManager, MessageManager messageManager){
        this.groupChatManager = groupChatManager;
        this.userManager = userManager;
        this.userController = userController;
        this.presenter = new GroupChatPresenter();
        this.messageManager = messageManager;
    }
    public void showGroupChats(String name){
        String group = presenter.printMyGroupChats(groupChatManager.getMyGroupChats(userManager.getUser(name)));
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
