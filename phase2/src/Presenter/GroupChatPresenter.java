package Presenter;

import java.util.List;
import java.util.Scanner;

public class GroupChatPresenter extends Presenter {
    private Scanner sc = new Scanner(System.in);

    public String[] printGroupChatNames(){
        System.out.println("Enter the names of the people you are inviting to the group chat separated with a comma");
        return sc.nextLine().split(",");
    }

    public String printGroupName(){
        System.out.println("Enter the name of the group chat");
        return sc.nextLine();
    }

    public String printMyGroupChats(List<String> groups){
        while (true){
            if (groups.size() > 0){
                for (String group: groups){
                    System.out.println(group);
                }
            }
            else{
                System.out.println("You are not in any group chats");
                return null;
            }
            System.out.println("Enter the group chat you want to visit or enter 0 to go back to the main menu");
            String input = sc.nextLine();
            if (input.equals("0") || groups.contains(input)){
                return input;
            }
            printInvalidOption();
        }

    }

    public boolean printGroupChatMessages(List<String> messages){
        while (true){
            if (messages.size() == 0) {
                System.out.println("There aren't any messages yet");
            }
            else{
                for (String message: messages){
                    System.out.println(message);
                }
            }
            System.out.println("Enter 1 to send a message or enter 2 to exit the groupchat");
            String input = sc.nextLine();
            if (input.equals("1")){
                return true;
            }
            if (input.equals("2")){
                return false;
            }
            printInvalidOption();
        }

    }
}
