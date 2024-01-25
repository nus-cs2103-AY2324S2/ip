import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a task with an identifier, title, and completion status.
 * Each task has a unique ID, a title, and a boolean flag to mark it as done or
 * not.
 */
class Task {
    private static int count = 0;
    private final int id;
    private String title;
    private boolean isDone;

    /**
     * Constructor to create a new task.
     * Initializes the task with a unique ID and the specified title.
     * The task is initially marked as not done.
     *
     * @param title The title of the task.
     */
    public Task(String title) {
        this.id = ++count;
        this.title = title;
        this.isDone = false;
    }

    /**
     * Retrieves the unique ID of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Provides a string representation of the task.
     * Format: "[\u2713] Title" for completed tasks, "[\u2718] Title" for pending
     * tasks.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + title;
    }
}

/**
 * Class representing a Todo task.
 * Inherits from the Task class and adds specific formatting to the toString
 * method.
 */
class Todo extends Task {
    /**
     * Constructor to create a new Todo task.
     * Calls the constructor of the parent Task class.
     *
     * @param title The title of the todo task.
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Provides a string representation of the Todo task.
     * Format: "[T][\u2713 or \u2718] Title" for completed or pending tasks
     * respectively.
     *
     * @return The formatted string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Class representing a Deadline task with a due date.
 * Inherits from the Task class and adds a deadline date.
 */
class Deadline extends Task {
    private String by;

    /**
     * Constructor to create a new Deadline task.
     * Initializes the task with a title and a deadline date.
     *
     * @param title The title of the deadline task.
     * @param by    The deadline date for the task.
     */
    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    /**
     * Provides a string representation of the Deadline task.
     * Format: "[D][\u2713 or \u2718] Title (by: deadline date)" for completed or
     * pending tasks respectively.
     *
     * @return The formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

/**
 * Class representing an Event task with a location or time.
 * Inherits from the Task class and adds event details.
 */
class Event extends Task {
    private String at;

    /**
     * Constructor to create a new Event task.
     * Initializes the task with a title and event details.
     *
     * @param title The title of the event task.
     * @param at    The location or time of the event.
     */
    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    /**
     * Provides a string representation of the Event task.
     * Format: "[E][\u2713 or \u2718] Title (at: event details)" for completed or
     * pending tasks respectively.
     *
     * @return The formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

public class Duke {
    public static void main(String[] args) {
        // Greet user
        String logo = "\n /$$      /$$  /$$$$$$  /$$$$$$$  /$$$$$$$$ /$$     /$$\n"
                + "| $$$    /$$$ /$$__  $$| $$__  $$|__  $$__/|  $$   /$$/\n"
                + "| $$$$  /$$$$| $$  \\ $$| $$  \\ $$   | $$    \\  $$ /$$/ \n"
                + "| $$ $$/$$ $$| $$  | $$| $$$$$$$/   | $$     \\  $$$$/  \n"
                + "| $$  $$$| $$| $$  | $$| $$__  $$   | $$      \\  $$/   \n"
                + "| $$\\  $ | $$| $$  | $$| $$  \\ $$   | $$       | $$    \n"
                + "| $$ \\/  | $$|  $$$$$$/| $$  | $$   | $$       | $$    \n"
                + "|__/     |__/ \\______/ |__/  |__/   |__/       |__/    \n";
        System.out.println("\nHello I'm\n" + logo);
        System.out.println("What can I do for you?\n");

        List<Task> tasks = new ArrayList<Task>();

        // Main loop for command processing
        while (true) {

            // Read and process user input
            String command = System.console().readLine();
            String[] tokens = command.split(" ", 2);
            System.out.println("\n============================================================\n");

            // Handle different commands
            if (command.equals("bye")) {
                // Exit program
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // List all tasks
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
            } else if (tokens[0].equals("todo")) {
                // Add a new todo task
                String title = tokens[1];
                Todo newTodo = new Todo(title);
                tasks.add(newTodo);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTodo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (tokens[0].equals("deadline")) {
                // Add a new deadline task
                String[] deadlineTokens = tokens[1].split(" /by ");
                String title = deadlineTokens[0];
                String by = deadlineTokens[1];
                Deadline newDeadline = new Deadline(title, by);
                tasks.add(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (tokens[0].equals("event")) {
                // Add a new event task
                String[] eventTokens = tokens[1].split(" /at ");
                String title = eventTokens[0];
                String at = eventTokens[1];
                Event newEvent = new Event(title, at);
                tasks.add(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println(newEvent.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (tokens[0].equals("done")) {
                // Mark a task as done
                int i = Integer.parseInt(tokens[1]) - 1;
                tasks.get(i).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(i).toString());
            } else {
                // Add a new task with no specific type
                Task newTask = new Task(command);
                tasks.add(newTask);
                System.out.println("added: " + newTask.toString());
            }
            System.out.println("\n============================================================\n");
        }
    }
}
