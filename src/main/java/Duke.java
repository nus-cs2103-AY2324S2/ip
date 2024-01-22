import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(
                "Hello! I'm Bob\n" +
                "What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String command = userInput.nextLine();
            if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else {
                System.out.println("added: "+command);
                tasks[taskCount] = command;
                taskCount++;
            }
        }
    }
}
