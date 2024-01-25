import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class representing my chatbot application.
 */
public class Rochin {
    public static void main(String[] args) throws RochinException {
        Chatbot chatbot = new Chatbot();
        chatbot.start();
    }
}

/**
 * Represent a chatbot with more abilities.
 */
class Chatbot {
    private final List<Task> tasks;


    /**
     * Construct a Chatbot and initialize the task storage array.
     */
    public Chatbot() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Start the chat.
     */
    public void start() throws RochinException {
        greetMessage();
        processCommands();
        exitMessage();
    }

    private void greetMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Rochin.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void exitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private void processCommands() throws RochinException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String userInput = scanner.nextLine();

            CommandProcessor commandProcessor = new CommandProcessor(userInput);

            if (commandProcessor.isExitCommand()) {
                break;
            }

            commandProcessor.process();
        }

        scanner.close();
    }

    /**
     * Add a new task to the task storage array and displays a message.
     *
     * @param newTask The task to be added.
     */
    private void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Display the list of stored tasks with their status.
     */
    private void listTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Remove the specified task from the list.
     *
     * @param taskIndex The index of the task to be removed.
     */
    private void deleteTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Mark the specified task as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    private void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tasks.get(taskIndex - 1));
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Mark the specified task as not done.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    private void unmarkTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsNotDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + tasks.get(taskIndex - 1));
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Check if the task index is valid.
     *
     * @param taskIndex The index of the task to be checked.
     */
    private boolean isValidTaskIndex(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            return true;
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Invalid task index. Please enter a valid index.");
            System.out.println("    ____________________________________________________________");
            return false;
        }
    }

    /**
     * Represent a command processor for processing user commands and managing tasks.
     */
    private class CommandProcessor {

        private final String command;
        private boolean isExitCommand;

        /**
         * Construct a CommandProcessor with the given user command.
         *
         * @param command The user command to be processed.
         */
        public CommandProcessor(String command) {
            this.command = command;
        }

        /**
         * Processe the user command.
         */
        public void process() throws RochinException {
            if (!isExitCommand) {
                if (command.startsWith("list")) {
                    listTasks();
                } else if (command.startsWith("todo")) {
                    String description = command.substring("todo".length()).trim();
                    if (description.isEmpty()) {
                        throw new RochinException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTask(new TodoTask(description));
                } else if (command.startsWith("deadline")) {
                    String descriptionWithDate = command.substring("deadline".length()).trim();
                    addTask(DeadlineTask.createTask(descriptionWithDate));
                } else if (command.startsWith("event")) {
                    String descriptionWithDate = command.substring("event".length()).trim();
                    addTask(EventTask.createTask(descriptionWithDate));
                } else if (command.startsWith("delete")) {
                    int taskIndex = getTaskIndex("delete");
                    deleteTask(taskIndex);
                } else if (command.startsWith("mark")) {
                    int taskIndex = getTaskIndex("mark");
                    markTaskAsDone(taskIndex);
                } else if (command.startsWith("unmark")) {
                    int taskIndex = getTaskIndex("unmark");
                    unmarkTaskAsDone(taskIndex);
                } else {
                    throw new RochinException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }

        private int getTaskIndex(String operation) {
            try {
                String[] splitCommand = command.split("\\s+");
                return Integer.parseInt(splitCommand[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Invalid " + operation + " command. Please enter a valid task index.");
                System.out.println("    ____________________________________________________________");
                return -1;
            }
        }

        /**
         * Check if the user command is an exit command.
         *
         * @return true if the user command is "bye", false otherwise.
         */
        public boolean isExitCommand() {
            isExitCommand = command.equalsIgnoreCase("bye");
            return isExitCommand;
        }
    }
}

/**
 * Represent a task with a description and a status.
 */
class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Construct a task with the given description and set its status to not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon representing whether the task is done or not done.
     *
     * @return The status icon ('X' if done, ' ' if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

/**
 * Represent a Todo task.
 */
class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Represent a Deadline task.
 */
class DeadlineTask extends Task {

    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Return a new deadline task.
     *
     * @param descriptionWithDate description with date.
     * @return A new deadline task.
     */
    public static DeadlineTask createTask(String descriptionWithDate) throws RochinException {
        String[] parts = descriptionWithDate.split("/by");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String by = parts[1].trim();
            return new DeadlineTask(description, by);
        } else {
            throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
        }
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

/**
 * Represent an Event task.
 */
class EventTask extends Task {

    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Return a new event task.
     *
     * @param descriptionWithDate description with date.
     * @return A new event task.
     */
    public static EventTask createTask(String descriptionWithDate) throws RochinException {
        String[] parts = descriptionWithDate.split("/from");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String[] dateRange = parts[1].split("/to");
            if (dateRange.length == 2) {
                String from = dateRange[0].trim();
                String to = dateRange[1].trim();
                return new EventTask(description, from, to);
            }
        }
        throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

/**
 * Represents exceptions specific to Rochin.
 */
class RochinException extends Exception {

    /**
     * Constructs an exception with the specified error message.
     *
     * @param message The error message.
     */
    public RochinException(String message) {
        super(message);
    }
}
