import java.util.Scanner;

public class Duke {
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
                System.out.println(" Here" + (taskCount == 1 ? " is the " : " are the ") + taskCount +
                        (taskCount == 1 ? " task " : " tasks ") + "in your list:");
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
                tasks[taskCount++] = createTask(userInput);
                // Display confirmation
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                //System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(" Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
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

    // Create a task based on user input
    private static Task createTask(String userInput) {
        String[] inputParts = userInput.split(" ", 2);
        String taskType = inputParts[0].toLowerCase();

        switch (taskType) {
            case "todo":
                return new Todo(inputParts[1]);
            case "deadline":
                return createDeadlineTask(inputParts[1]);
            case "event":
                return createEventTask(inputParts[1]);
            default:
                return new Task(userInput); // Default to a generic task
        }
    }

    // Create a deadline task based on user input
    private static Task createDeadlineTask(String input) {
        String[] parts = input.split("/by", 2);
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    // Create an event task based on user input
    private static Task createEventTask(String input) {
        String[] parts = input.split("/from", 2);
        String[] dateParts = parts[1].trim().split("/to", 2);
        return new Event(parts[0].trim(), dateParts[0].trim(), dateParts[1].trim());
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getStatusIcon() {
        return " "; // Default to empty space
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "T"; // Todo tasks have "T" as their status icon
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "D"; // Deadline tasks have "D" as their status icon
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "E"; // Event tasks have "E" as their status icon
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


