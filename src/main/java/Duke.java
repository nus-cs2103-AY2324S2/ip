import java.util.Scanner;

public class Duke {
    // Fixed-size array to store tasks
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        // Greeting
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duck");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main chat loop
        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Process user input
            if (userInput.equalsIgnoreCase("bye")) {
                // Farewell
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            } else if (userInput.equalsIgnoreCase("list")) {
                // Display the list of tasks
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                // Mark a task as done
                int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
                markTaskAsDone(taskIndex);
            } else if (userInput.startsWith("unmark")) {
                // Mark a task as not done
                int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
                unmarkTaskAsDone(taskIndex);
            } else {
                // Add the task to the array
                tasks[taskCount++] = new Task(userInput, false);
                // Display confirmation
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Mark a task as done
    private static void markTaskAsDone(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].setDone(true);
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[index]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }

    // Mark a task as not done
    private static void unmarkTaskAsDone(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].setDone(false);
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[index]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
