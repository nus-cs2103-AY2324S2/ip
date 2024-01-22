import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(
                "Hello! I'm Bob\n" +
                "What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String command = userInput.nextLine();
            if (command.equals("list")) {
                System.out.println("Here are your tasks in your list");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + "." + tasks[i]);
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (command.contains("unmark")) {
                Task t = tasks[Integer.valueOf(command.split(" ")[1]) - 1];
                t.unmark();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(t);
            } else if (command.contains("mark")) {
                Task t = tasks[Integer.valueOf(command.split(" ")[1]) - 1];
                t.mark();
                System.out.println("Nice, I've marked this task as done:");
                System.out.println(t);
            } else {
                System.out.println("added: "+command);
                Task t = new Task(command);
                tasks[taskCount] = t;
                taskCount++;
            }
        }
    }
}
