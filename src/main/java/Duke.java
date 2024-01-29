import java.util.Scanner;

public class Duke {
    private static final int MAX_NO_OF_TASKS = 100;
    private static Task[] tasks = new Task[MAX_NO_OF_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm EchoPilot.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (command.equalsIgnoreCase("mark") && parts.length > 1) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[index].toString());
                System.out.println("____________________________________________________________");
            } else if (command.equalsIgnoreCase("unmark") && parts.length > 1) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks[index].unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index].toString());
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task(userInput);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}
