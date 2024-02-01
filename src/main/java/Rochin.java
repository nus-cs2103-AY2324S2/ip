import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class representing my chatbot application.
 */
public class Rochin {



    public static void main(String[] args) throws RochinException {
        Chatbot chatbot = new Chatbot();
        chatbot.loadData();
        chatbot.start();
        chatbot.saveData();
    }
}

/**
 * Represent a chatbot with more functions.
 */
class Chatbot {
    private final List<Task> tasks;
    private static final String FILE_PATH = "./data/rochin.txt";

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

    /**
     * Save tasks to the file.
     */
    public void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load tasks from the file.
     */
    public void loadData() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String fileLine = scanner.nextLine();
                    Task task = Task.createTaskFromFileString(fileLine);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            } else {
                createDataFileIfNotExists();
            }
        } catch (FileNotFoundException | RochinException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new data file.
     */
    public void createDataFileIfNotExists() {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Failed to create data folder. Exiting.");
                System.exit(1);
            }

            if (!file.createNewFile()) {
                System.out.println("Failed to create data file. Exiting.");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * The string representation of the task that to be saved in the file.
     *
     * @return The string representation of the task.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s", getTaskType(), isDone ? 1 : 0, description, getAdditionalDetails());
    }


    /**
     * Create new tasks according to the data in the file.
     *
     * @param fileLine data from the file.
     * @return new task.
     */
    public static Task createTaskFromFileString(String fileLine) throws RochinException {
        String[] parts = fileLine.split("\\s*\\|\\s*");
        if (parts.length >= 3) {
            boolean isDone = Integer.parseInt(parts[1]) == 1;
            String description = parts[2];
            // Extract additional details based on task type (Todo, Deadline, Event)
            // String additionalDetails = parts.length > 3 ? parts[3] : null;
            if (parts[0].equals("T")) {
                TodoTask todoTask = new TodoTask(description);
                if (isDone) {
                    todoTask.markAsDone();
                }
                return todoTask;
            } else if (parts[0].equals("D")) {
                description = parts[2] + parts[3];
                DeadlineTask deadlineTask = DeadlineTask.createTask(description);
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                return deadlineTask;
            } else if (parts[0].equals("E")) {
                description = parts[2] + parts[3];
                EventTask eventTask = EventTask.createTask(description);
                if (isDone) {
                    eventTask.markAsDone();
                }
                return eventTask;
            }
        }
        return null;
    }

    /**
     * Default implementation of getTaskType, can be overridden by subclasses
     *
     * @return A string.
     */
    public String getTaskType() {
        return "U";
    }

    /**
     * Default implementation of getAdditionalDetails, can be overridden by subclasses
     *
     * @return A string.
     */
    public String getAdditionalDetails() {
        return "";
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

    @Override
    public String getAdditionalDetails() {
        return ""; 
    }

    @Override
    public String getTaskType() {
        return "T";
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

    private LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
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
        try {
            String[] parts = descriptionWithDate.split("/by");
            if (parts.length == 2) {
                String description = parts[0].trim();
                LocalDate by = LocalDate.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new DeadlineTask(description, by);
            } else {
                throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
            }
        } catch (DateTimeParseException e) {
            throw new RochinException("OOPS!!! Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s", getTaskType(), isDone ? 1 : 0, description, by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

/**
 * Represent an Event task.
 */
class EventTask extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public EventTask(String description, LocalDate from, LocalDate to) {
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
        try {
            String[] parts = descriptionWithDate.split("/from");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String[] dateRange = parts[1].split("/to");
                LocalDate from = LocalDate.parse(dateRange[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate to = LocalDate.parse(dateRange[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new EventTask(description, from, to);
            } else {
                throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
            }
        } catch (DateTimeParseException e) {
            throw new RochinException("OOPS!!! Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s to %s", getTaskType(), isDone ? 1 : 0, description, from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Return a string representation of the task type.
     *
     * @return A string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return "E";
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
