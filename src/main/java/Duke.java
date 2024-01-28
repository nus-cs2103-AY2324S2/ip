import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Declare Scanner instance

        Task[] tasks = new Task[100];
        int taskCount = 0;

        String chatbotName = "Jamie";
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");

        while (true) {
            System.out.print(">> ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
                }

            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());

                } else {
                    System.out.println("Invalid task index.");
                }

            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) -1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsUndone();
                    System.out.println("Ok, I've marked this task as not done yet: \n [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                } else {
                    System.out.println("Invalid task index.");
                }

            } else {
                tasks[taskCount++] = new Task (userInput);
                System.out.println(" added: " + userInput);
            }
        }
        scanner.close(); // Close the Scanner to avoid resource leaks
    }
}


class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }
}
