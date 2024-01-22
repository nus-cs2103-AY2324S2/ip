import java.util.Scanner;

public class Atlas {
    private static final int MAX_TASKS = 100;
    private final String horizontalLine = "____________________________________________________________";
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCounter = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                listTasks();
            }
            else {
                addTask(input);
            }

        }
        exit();

    }

    private static void greet() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(String task) {
        tasks[taskCounter] = task;
        taskCounter++;
        System.out.println(" added: " + task);
    }

    private static void listTasks() {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
