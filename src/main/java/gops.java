import java.util.Scanner;
public class gops {
    public static void main(String[] args) {
        Scanner inputTaker = new Scanner(System.in);
        System.out.println("Hello! I'm gops");
        System.out.println("What can I do for you?");
        String userReply = inputTaker.nextLine();
        while (userReply != null && !userReply.equals("bye")) {
            System.out.println(userReply);
            userReply = inputTaker.nextLine();
        }
        if (userReply.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
