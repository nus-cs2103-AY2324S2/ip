import java.util.Scanner;

public class gops {
    public static void main(String[] args) {
        Scanner inputTaker = new Scanner(System.in);
        String[] userDataArray = new String[100];
        int messageCount = 0;

        System.out.println("Hello! I'm gops");
        System.out.println("What can I do for you?");

        String userReply = inputTaker.nextLine();
        while (userReply != null && !userReply.equals("bye")) {
            if (userReply.equals("list")) {
                for (int i = 0; i < messageCount; i++) {
                    System.out.println(i + 1 + ". " + userDataArray[i]);
                }
                userReply = inputTaker.nextLine();
            } else {
                userDataArray[messageCount] = userReply;
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