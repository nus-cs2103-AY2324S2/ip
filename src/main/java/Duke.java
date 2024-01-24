import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm SCZL");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println(" added: " + task);
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");

        if (taskCount == 0) {
            System.out.println(" No tasks yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
    }
}