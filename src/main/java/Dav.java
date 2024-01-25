import java.util.Scanner;

public class Dav {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greetUser();
        startChat();
        exit();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Dav");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = getUserInput(scanner);
            processUserInput(userInput);

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    public static String getUserInput(Scanner scanner) {
        System.out.print("    ");
        return scanner.nextLine();
    }

    public static void processUserInput(String input) {

        if (input.equalsIgnoreCase("list")) {
            listTasks();
        } else if (!input.equalsIgnoreCase("bye")) {
            addTask(input);
        }

        System.out.println("____________________________________________________________");
    }

    public static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println(" added: " + task);
        } else {
            System.out.println(" Sorry, task list is full. Cannot add more tasks.");
        }
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println(" No tasks added yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
    }

    public static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}


