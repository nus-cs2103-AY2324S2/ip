package rochinBot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Main class representing the RochinOOP application.
 */
public class RochinOOP {

    private static final String FILE_PATH = "./data/rochin.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public RochinOOP() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList();
    }

    /**
     * Method to start the RochinOOP application.
     */
    public void run() {
        ui.showWelcomeMessage();
        loadTasks();
        processCommands();
        saveTasks();
        ui.showGoodbyeMessage();
    }

    /**
     * Main method to launch the RochinOOP application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new RochinOOP().run();
    }

    /**
     * Method to process user commands.
     */
    public void processCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ui.showCommandPrompt();
            String userInput = scanner.nextLine();
            CommandProcessor commandProcessor = new CommandProcessor(userInput);

            if (commandProcessor.isExitCommand()) {
                break;
            }

            commandProcessor.process(tasks, ui);
        }

        scanner.close();
    }

    /**
     * Method to load tasks from the storage.
     */
    public void loadTasks() {
        try {
            tasks.load(storage.load());
        } catch (RochinException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Method to save tasks to the storage.
     */
    public void saveTasks() {
        try {
            storage.save(tasks.convertTasksToStrings());
        } catch (RochinException e) {
            ui.showSavingError();
        }
    }

    /**
     * Inner class representing the user interactions.
     */
    public class Ui {

        /**
         * Display the welcome message.
         */
        public void showWelcomeMessage() {
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm Rochin.");
            System.out.println("What can I do for you?");
            System.out.println("____________________________________________________________");
        }

        /**
         * Display the goodbye message.
         */
        public void showGoodbyeMessage() {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }

        /**
         * Display the command prompt.
         */
        public void showCommandPrompt() {
            System.out.print("Enter a command: ");
        }

        /**
         * Display an error message for loading tasks.
         */
        public void showLoadingError() {
            System.out.println("Failed to load tasks. Creating a new task list.");
        }

        /**
         * Display an error message for saving tasks.
         */
        public void showSavingError() {
            System.out.println("Failed to save tasks.");
        }

        /**
         * Display a generic error message with the provided errorMessage.
         *
         * @param errorMessage The error message to be displayed.
         */
        public void showError(String errorMessage) {
            System.out.println("Error: " + errorMessage);
        }

        /**
         * Display an unknown command error message.
         */
        public void showUnknownCommandError() {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        /**
         * Display an invalid command error message.
         */
        public void showInvalidCommandError() {
            System.out.println("Invalid command. Please enter a valid command.");
        }

        /**
         * Display the list of tasks.
         *
         * @param tasks The list of tasks to be displayed.
         */
        public void showTaskList(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }

        /**
         * Display a message confirming the addition of a task.
         *
         * @param tasks The updated list of tasks.
         */
        public void showTaskAddedMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }

        /**
         * Display a message confirming the deletion of a task.
         *
         * @param tasks The updated list of tasks.
         */
        public void showTaskDeletedMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }

        /**
         * Display a message confirming the marking of a task as done.
         *
         * @param tasks The updated list of tasks.
         */
        public void showTaskMarkedAsDoneMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("____________________________________________________________");
        }

        /**
         * Display a message confirming the marking of a task as not done.
         *
         * @param tasks The updated list of tasks.
         */
        public void showTaskUnmarkedAsDoneMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * The Storage class handles loading and saving tasks to a file.
     */
    public class Storage {
        private final String filePath;

        /**
         * Constructor for the Storage class.
         *
         * @param filePath The path to the file where tasks are stored.
         */
        public Storage(String filePath) {
            this.filePath = filePath;
        }

        /**
         * Load tasks from the specified file.
         *
         * @return A list of strings representing the loaded tasks.
         * @throws RochinException If an error occurs while loading tasks from the file.
         */
        public List<String> load() throws RochinException {
            List<String> lines = new ArrayList<>();
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    reader.close();
                }
            } catch (IOException e) {
                throw new RochinException("Error loading tasks from file.");
            }
            return lines;
        }

        /**
         * Save tasks to the specified file.
         *
         * @param lines The list of strings representing tasks to be saved.
         * @throws RochinException If an error occurs while saving tasks to the file.
         */
        public void save(List<String> lines) throws RochinException {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.println(line);
                }
            } catch (IOException e) {
                throw new RochinException("Error saving tasks to file.");
            }
        }
    }

    /**
     * Represent a list of tasks.
     */
    public class TaskList {
        private final List<Task> tasks;

        /**
         * Construct an empty TaskList.
         */
        public TaskList() {
            this.tasks = new ArrayList<>();
        }

        public TaskList(List<Task> tasks) {
            this.tasks = tasks;
        }

        /**
         * Add a new task to the TaskList
         *
         * @param newTask The task to be added.
         */
        public void addTask(Task newTask) {
            tasks.add(newTask);
        }

        /**
         * Delete a task from the TaskList based on its index.
         *
         * @param taskIndex The index of the task to be deleted.
         */
        public void deleteTask(int taskIndex) {
            if (isValidTaskIndex(taskIndex)) {
                tasks.remove(taskIndex - 1);
            }
        }

        /**
         * Mark a task as done based on its index.
         *
         * @param taskIndex The index of the task to be marked as done.
         */
        public void markTaskAsDone(int taskIndex) {
            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex - 1).markAsDone();
            }
        }

        /**
         * Unmark a task as done based on its index.
         *
         * @param taskIndex The index of the task to be marked as not done.
         */
        public void unmarkTaskAsDone(int taskIndex) {
            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex - 1).markAsNotDone();
            }
        }

        /**
         * Retrieve a copy of all tasks in the TaskList.
         *
         * @return A list containing all tasks.
         */
        public List<Task> getAllTasks() {
            return new ArrayList<>(tasks);
        }

        private boolean isValidTaskIndex(int taskIndex) {
            return taskIndex >= 1 && taskIndex <= tasks.size();
        }

        /**
         * Load tasks from a list of strings.
         *
         * @param lines The list of strings representing tasks.
         * @throws RochinException if there's an error during loading.
         */
        public void load(List<String> lines) throws RochinException {
            for (String line : lines) {
                Task task = new Task(line);
                task.createTaskFromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        /**
         * Convert all tasks to a list of strings.
         *
         * @return A list of strings representing tasks.
         */
        public List<String> convertTasksToStrings() {
            List<String> taskStrings = new ArrayList<>();
            for (Task task : tasks) {
                taskStrings.add(task.toFileString());
            }
            return taskStrings;
        }

        /**
         * Search for tasks containing the specified keyword.
         *
         * @param keyword The keyword to search for in task descriptions.
         * @return A list of tasks that match the given keyword.
         */
        public List<Task> findTasks(String keyword) {
            List<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }
            return matchingTasks;
        }
    }

    /**
     * Represent a command processor for handling user commands related to tasks.
     */
    public class CommandProcessor {
        private final String command;
        private boolean isExitCommand;

        /**
         * Construct a CommandProcessor with the given command.
         *
         * @param command The command to be processed.
         */
        public CommandProcessor(String command) {
            this.command = command;
        }

        /**
         * Process the command based on its operation.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void process(TaskList tasks, Ui ui) {
            if (!isExitCommand) {
                String[] splitCommand = command.split("\\s+");
                String operation = splitCommand[0].toLowerCase();

                switch (operation) {
                    case "list":
                        ui.showTaskList(tasks.getAllTasks());
                        break;
                    case "todo":
                        processTodoCommand(tasks, ui);
                        break;
                    case "deadline":
                        processDeadlineCommand(tasks, ui);
                        break;
                    case "event":
                        processEventCommand(tasks, ui);
                        break;
                    case "delete":
                        processDeleteCommand(tasks, ui);
                        break;
                    case "mark":
                        processMarkCommand(tasks, ui);
                        break;
                    case "unmark":
                        processUnmarkCommand(tasks, ui);
                        break;
                    case "find":
                        processFindCommand(tasks, ui);
                        break;
                    default:
                        ui.showUnknownCommandError();
                }
            }
        }

        /**
         * Process a "todo" command and adds a new todo task to the TaskList.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void processTodoCommand(TaskList tasks, Ui ui) {
            try {
                String description = command.substring("todo".length()).trim();
                if (description.isEmpty()) {
                    throw new RochinException("OOPS!!! The description of a todo cannot be empty.");
                }
                tasks.addTask(new TodoTask(description));
                ui.showTaskAddedMessage(tasks.getAllTasks());
            } catch (RochinException e) {
                ui.showError(e.getMessage());
            }
        }

        /**
         * Process a "deadline" command and adds a new deadline task to the TaskList.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void processDeadlineCommand(TaskList tasks, Ui ui) {
            try {
                String descriptionWithDate = command.substring("deadline".length()).trim();
                // Splitting the description and deadline by "/by"
                String[] parts = descriptionWithDate.split("/by");
                if (parts.length != 2) {
                    throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
                }
                String description = parts[0].trim();
                String deadline = parts[1].trim();
                // Parse the deadline string to LocalDateTime
                LocalDateTime deadlineDateTime = DateAndTime.parseDateTime(deadline);
                DeadlineTask ddlTask = new DeadlineTask(description, deadlineDateTime);
                tasks.addTask(ddlTask);
                ui.showTaskAddedMessage(tasks.getAllTasks());
            } catch (RochinException e) {
                ui.showError(e.getMessage());
            }
        }

        /**
         * Process an "event" command and adds a new event task to the TaskList.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void processEventCommand(TaskList tasks, Ui ui) {
            try {
                String descriptionWithDate = command.substring("event".length()).trim();
                // Splitting the description, starting datetime, and ending datetime by "/from" and "/to"
                String[] parts = descriptionWithDate.split("/from");
                if (parts.length != 2) {
                    throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
                }
                String description = parts[0].trim();
                String[] dateTimeParts = parts[1].split("/to");
                if (dateTimeParts.length != 2) {
                    throw new RochinException("OOPS!!! Please provide both starting and ending date/time for the event.");
                }
                String fromDateTime = dateTimeParts[0].trim();
                String toDateTime = dateTimeParts[1].trim();
                // Parse the starting and ending date/time strings to LocalDateTime
                LocalDateTime fromDateTimeObject = DateAndTime.parseDateTime(fromDateTime);
                LocalDateTime toDateTimeObject = DateAndTime.parseDateTime(toDateTime);
                EventTask eventTask = new EventTask(description, fromDateTimeObject, toDateTimeObject);
                tasks.addTask(eventTask);
                ui.showTaskAddedMessage(tasks.getAllTasks());
            } catch (RochinException e) {
                ui.showError(e.getMessage());
            }
        }

        /**
         * Process a "delete" command and deletes a task from the TaskList.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void processDeleteCommand(TaskList tasks, Ui ui) {
            int taskIndex = getTaskIndex();
            tasks.deleteTask(taskIndex);
            ui.showTaskDeletedMessage(tasks.getAllTasks());
        }

        /**
         * Process a "mark" command and marks a task as done in the TaskList.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void processMarkCommand(TaskList tasks, Ui ui) {
            int taskIndex = getTaskIndex();
            tasks.markTaskAsDone(taskIndex);
            ui.showTaskMarkedAsDoneMessage(tasks.getAllTasks());
        }

        /**
         * Process an "unmark" command and unmarks a task as done in the TaskList.
         *
         * @param tasks The TaskList to be modified.
         * @param ui    The user interface for displaying messages.
         */
        public void processUnmarkCommand(TaskList tasks, Ui ui) {
            int taskIndex = getTaskIndex();
            tasks.unmarkTaskAsDone(taskIndex);
            ui.showTaskUnmarkedAsDoneMessage(tasks.getAllTasks());
        }

        /**
         * Extract the task index from the command.
         *
         * @return The index of the task.
         */
        public int getTaskIndex() {
            try {
                String[] splitCommand = command.split("\\s+");
                return Integer.parseInt(splitCommand[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.showInvalidCommandError();
                return -1;
            }
        }

        /**
         * Process the "find" command, searching for tasks containing the specified keyword.
         *
         * @param tasks The TaskList containing all tasks.
         * @param ui    The Ui for displaying user interface messages.
         */
        public void processFindCommand(TaskList tasks, Ui ui) {
            try {
                String keyword = command.substring("find".length()).trim();
                if (keyword.isEmpty()) {
                    throw new RochinException("OOPS!!! Please provide a keyword to search for.");
                }
                List<Task> matchingTasks = tasks.findTasks(keyword);
                ui.showTaskList(matchingTasks);
            } catch (RochinException e) {
                ui.showError(e.getMessage());
            }
        }

        /**
         * Check if the command is an exit command.
         *
         * @return True if the command is an exit command, false otherwise.
         */
        public boolean isExitCommand() {
            isExitCommand = command.equalsIgnoreCase("bye");
            return isExitCommand;
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
         * Mark the task as done
         */
        public void markAsDone() {
            isDone = true;
        }

        /**
         * Mark the task as not done
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
         * @return new task
         */
        public Task createTaskFromFileString(String fileLine) throws RochinException {
            String[] parts = fileLine.split("\\s* \\| \\s*");
            if (parts.length >= 3) {
                boolean isDone = Integer.parseInt(parts[1]) == 1;
                String description = parts[2];
                LocalDateTime dateTime = null;
                if (parts.length > 3) {
                    String dateTimeString = parts[3];
                    dateTime = DateAndTime.parseDateTime(dateTimeString); // Assuming storage is accessible here
                }
                // Extract additional details based on task type (Todo, Deadline, Event)
                String additionalDetails = parts.length > 3 ? parts[3] : null;

                if (parts[0].equals("T")) {
                    TodoTask todoTask = new TodoTask(description);
                    if (isDone) {
                        todoTask.markAsDone();
                    }
                    return todoTask;
                } else if (parts[0].equals("D")) {
                    DeadlineTask deadlineTask = new DeadlineTask(description, dateTime);
                    if (isDone) {
                        deadlineTask.markAsDone();
                    }
                    return deadlineTask;
                } else if (parts[0].equals("E")) {
                    // Extract 'from' and 'to' datetime parameters
                    LocalDateTime fromDateTime = DateAndTime.parseDateTime(parts[3]);
                    LocalDateTime toDateTime = DateAndTime.parseDateTime(parts[4]);
                    EventTask eventTask = new EventTask(description, fromDateTime, toDateTime);
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

        protected LocalDateTime byDateTime;

        public DeadlineTask(String description, LocalDateTime byDateTime) {
            super(description);
            this.byDateTime = byDateTime;
        }

        /**
         * Return a new deadline task.
         *
         * @param descriptionWithDateTime description with date.
         * @return A new deadline task.
         */
        public DeadlineTask createTask(String descriptionWithDateTime) throws RochinException {
            String[] parts = descriptionWithDateTime.split("/by");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String dateTimeString = parts[1].trim();
                LocalDateTime byDateTime = DateAndTime.parseDateTime(dateTimeString);
                return new DeadlineTask(description, byDateTime);
            } else {
                throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
            }
        }

        /**
         * Return a string representation of the task type.
         *
         * @return A string representation of the task type.
         */
        @Override
        public String getTaskType() {
            return "D";
        }

        /**
         * Return a string representation of the task.
         *
         * @return A string representation of the task.
         */
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        }
    }

    /**
     * Represent an Event task.
     */
    class EventTask extends Task {

        protected LocalDateTime fromDateTime;
        protected LocalDateTime toDateTime;

        /**
         * Constructs an EventTask with a description, starting, and ending date/time.
         *
         * @param description The description of the event task.
         * @param fromDateTime        The starting date/time of the event.
         * @param toDateTime          The ending date/time of the event.
         */
        public EventTask(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
            super(description);
            this.fromDateTime = fromDateTime;
            this.toDateTime = toDateTime;
        }

        /**
         * Return a new event task.
         *
         * @param descriptionWithDateTime description with date.
         * @return A new event task.
         */
        public EventTask createTask(String descriptionWithDateTime) throws RochinException {
            String[] parts = descriptionWithDateTime.split("/from");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String[] dateTimeRange = parts[1].split("/to");
                if (dateTimeRange.length == 2) {
                    String fromDateTimeString = dateTimeRange[0].trim();
                    String toDateTimeString = dateTimeRange[1].trim();
                    LocalDateTime fromDateTime = DateAndTime.parseDateTime(fromDateTimeString);
                    LocalDateTime toDateTime = DateAndTime.parseDateTime(toDateTimeString);
                    return new EventTask(description, fromDateTime, toDateTime);
                }
            }
            throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
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
            return "[E]" + super.toString() + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) +
                    " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        }
    }
}
