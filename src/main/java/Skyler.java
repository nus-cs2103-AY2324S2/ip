import java.util.Scanner;

public class Skyler {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String chatbotName = "Skyler";
        String line = "------------------------------------------------------------";

        System.out.println("   /\\_/\\");
        System.out.println("  ( o.o ) Hello! I'm " + chatbotName);
        System.out.println("   > ^ < What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            System.out.println(line);

            if (userInput.equals("bye")) {
                System.out.println("Skyler: Bye. Hope to see you again soon!");
                System.out.println(line);
                break; // Exit the loop when "bye" is entered
            } else if (userInput.equals("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String task) {
        tasks[taskCount++] = task;
        System.out.println("Skyler: added: " + task);
    }

    private static void listTasks() {
        System.out.println("Skyler: Tasks");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("------------------------------------------------------------");
    }
}
