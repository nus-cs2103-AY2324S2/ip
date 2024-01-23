import java.util.List;
import java.util.Scanner;

public class gops {
    public static class ListItem {
        protected String itemDescription;
        protected boolean itemStatus = false;
        public ListItem(String itemDescription) {
            this.itemDescription = itemDescription;
        }
        public String doneOrNot() {
            if (itemStatus) {
                return "X";
            } else {
                return " ";
            }
        }
    }


    public static void main(String[] args) {
        Scanner inputTaker = new Scanner(System.in);
        ListItem[] toDoList = new ListItem[100];
        int messageCount = 0;

        System.out.println("Hello! I'm gops");
        System.out.println("What can I do for you?");

        String userReply = inputTaker.nextLine();
        while (userReply != null && !userReply.equals("bye")) {
            if (userReply.contains("unmark")) {
                int toDoListIndex =  Integer.parseInt(userReply.substring(userReply.length() -1)) - 1;
                toDoList[toDoListIndex].itemStatus = false;
                System.out.println("OK! I've marked this task as not done yet:");
                System.out.println("[" + toDoList[toDoListIndex].doneOrNot() + "] " + toDoList[toDoListIndex].itemDescription);
                userReply = inputTaker.nextLine();
            }
            else if (userReply.contains("mark")) {
                int toDoListIndex =  Integer.parseInt(userReply.substring(userReply.length() -1)) - 1;
                toDoList[toDoListIndex].itemStatus = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + toDoList[toDoListIndex].doneOrNot() + "] " + toDoList[toDoListIndex].itemDescription);
                userReply = inputTaker.nextLine();
            }

            else if (userReply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < messageCount; i++) {
                    System.out.println(i + 1 + "." + "[" + toDoList[i].doneOrNot() + "] " + toDoList[i].itemDescription);
                }
                userReply = inputTaker.nextLine();
            } else {
                ListItem toDo = new ListItem(userReply);
                toDoList[messageCount] = toDo;
                messageCount += 1;
                System.out.println("added: " + userReply);
                userReply = inputTaker.nextLine();
            }
        }
        if (userReply.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}