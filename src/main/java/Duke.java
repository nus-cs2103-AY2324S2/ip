import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

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
            try {
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
                    if (tasks.isEmpty()) {
                        System.out.println(" There are no tasks in your list.");
                    } else {
                        System.out.println(" Here" + (tasks.size() == 1 ? " is the " : " are the ") + tasks.size() +
                                (tasks.size() == 1 ? " task " : " tasks ") + "in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    // Mark a task as done
                    int taskIndex = parseTaskIndex(userInput);
                    markTaskAsDone(taskIndex);
                } else if (userInput.startsWith("unmark")) {
                    // Mark a task as not done
                    int taskIndex = parseTaskIndex(userInput);
                    unmarkTaskAsDone(taskIndex);
                } else if (userInput.trim().isEmpty()) {
                    throw new DukeException("Please enter an action and a task");
                } else if (userInput.startsWith("delete")) {
                    // Delete a task
                    int taskIndex = parseTaskIndex(userInput);
                    deleteTask(taskIndex);
                } else {
                    // Add the task to the array
                    tasks.add(createTask(userInput));
                    // Display confirmation
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                    System.out.println("____________________________________________________________");
                }
            } catch (DukeException e) {
                // Handle custom Duke exceptions
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (e.g., when parsing integers)
                System.out.println("____________________________________________________________");
                System.out.println("Please enter a valid task index.");
                System.out.println("____________________________________________________________");
            }
        }

        // Close the scanner
        scanner.close();
    }
    // Delete a task
    private static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }
    // Parse task index from user input
    private static int parseTaskIndex(String userInput) throws DukeException {
        try {
            // Assuming the input is in the format "mark 2" or "unmark 2"
            String indexString = userInput.split(" ", 2)[1].trim();
            return Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task index :(");
        }
    }

    // Mark a task as done
    private static void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(true);
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(index));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }

    // Mark a task as not done
    private static void unmarkTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(false);
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(index));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }

    // Create a task based on user input
    private static Task createTask(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        if(!(inputParts[0].equals("todo")) && !(inputParts[0].equals("deadline")) && !(inputParts[0].equals("event"))) {
            throw new DukeException("Don't talk nonsense");
        } else if (inputParts.length < 2) {
            throw new DukeException("What do you want to do");
        }

        String taskType = inputParts[0].toLowerCase();

        switch (taskType) {
            case "todo":
                return new Todo(inputParts[1]);
            case "deadline":
                return createDeadlineTask(inputParts[1]);
            case "event":
                return createEventTask(inputParts[1]);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
// Custom DukeException class
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}


