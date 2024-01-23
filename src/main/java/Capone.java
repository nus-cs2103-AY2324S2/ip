import java.util.Objects;
import java.util.Scanner;

public class Capone {
    // We assume there is no more than 100 tasks added.
    private static String[] tasks = new String[100];
    private static int taskCtr = 0;

    public static void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm \n%s\nWhat can I do for you?\n%n", logo);
    }

    public static void addTask(String task) {
        // Store task in array and increment counter.
        tasks[taskCtr] = task;
        taskCtr++;

        // Inform user that task has been added.
        System.out.printf("added: %s\n", task);
    }

    public static void listTasks() {
        for (int i = 0; i < taskCtr; i++) {
            System.out.printf("%d. %s\n", i+1, tasks[i]);
        }
    }

    public static void processInputs() {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Read the user input
        String input = scanner.nextLine();

        while (!input.equals("bye")) {

            // Add tasks to list. Else, list tasks.
            if (!input.equals("list")) {
                addTask(input);
            } else {
                listTasks();
            }

            // Read the user's next input.
            input = scanner.nextLine();
        }

        // If user entered "bye", exit program.
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {

        Capone.printWelcomeMsg();

        Capone.processInputs();
    }
}