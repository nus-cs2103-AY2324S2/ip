import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static boolean[] isDone = new boolean[MAX_TASKS];
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
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String task) {
        tasks[taskCount] = task;
        isDone[taskCount] = false;
        taskCount++;
        System.out.println(" added: " + task);
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");

        if (taskCount == 0) {
            System.out.println(" No tasks yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                char status = isDone[i] ? 'X' : ' ';
                System.out.println(" " + (i + 1) + ".[" + status + "] " + tasks[i]);
            }
        }
    }

    private static void markTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                isDone[taskIndex] = true;
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [X] " + tasks[taskIndex]);
            } else {
                System.out.println(" Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(" Invalid command format.");
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                isDone[taskIndex] = false;
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [ ] " + tasks[taskIndex]);
            } else {
                System.out.println(" Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(" Invalid command format.");
        }
    }
}