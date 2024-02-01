package rochin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void run() {
        ui.showWelcomeMessage();
        loadTasks();
        processCommands();
        saveTasks();
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new RochinOOP().run();
    }

    private void processCommands() {
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

    private void loadTasks() {
        try {
            tasks.load(storage.load());
        } catch (RochinException e) {
            ui.showLoadingError();
        }
    }

    private void saveTasks() {
        try {
            storage.save(tasks.convertTasksToStrings());
        } catch (RochinException e) {
            ui.showSavingError();
        }
    }

    private class Ui {
        public void showWelcomeMessage() {
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm Rochin.");
            System.out.println("What can I do for you?");
            System.out.println("____________________________________________________________");
        }

        public void showGoodbyeMessage() {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }

        public void showCommandPrompt() {
            System.out.print("Enter a command: ");
        }

        public void showLoadingError() {
            System.out.println("Failed to load tasks. Creating a new task list.");
        }

        public void showSavingError() {
            System.out.println("Failed to save tasks.");
        }

        public void showError(String errorMessage) {
            System.out.println("Error: " + errorMessage);
        }

        public void showUnknownCommandError() {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        public void showInvalidCommandError() {
            System.out.println("Invalid command. Please enter a valid command.");
        }

        public void showTaskList(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }

        public void showTaskAddedMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }

        public void showTaskDeletedMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }

        public void showTaskMarkedAsDoneMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("____________________________________________________________");
        }

        public void showTaskUnmarkedAsDoneMessage(List<Task> tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("____________________________________________________________");
        }
    }

    private class Storage {
        private final String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        public List<String> load() throws RochinException {
            List<String> lines = new ArrayList<>();
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        lines.add(scanner.nextLine());
                    }
                    scanner.close();
                }
            } catch (FileNotFoundException e) {
                throw new RochinException("Error loading tasks from file.");
            }
            return lines;
        }

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

    private class TaskList {
        private final List<Task> tasks;

        public TaskList() {
            this.tasks = new ArrayList<>();
        }

        public TaskList(List<Task> tasks) {
            this.tasks = tasks;
        }

        public void addTask(Task newTask) {
            tasks.add(newTask);
        }

        public void deleteTask(int taskIndex) {
            if (isValidTaskIndex(taskIndex)) {
                tasks.remove(taskIndex - 1);
            }
        }

        public void markTaskAsDone(int taskIndex) {
            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex - 1).markAsDone();
            }
        }

        public void unmarkTaskAsDone(int taskIndex) {
            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex - 1).markAsNotDone();
            }
        }

        public List<Task> getAllTasks() {
            return new ArrayList<>(tasks);
        }

        private boolean isValidTaskIndex(int taskIndex) {
            return taskIndex >= 1 && taskIndex <= tasks.size();
        }

        public void load(List<String> lines) throws RochinException {
            for (String line : lines) {
                Task task = new Task(line);
                task.createTaskFromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        public List<String> convertTasksToStrings() {
            List<String> taskStrings = new ArrayList<>();
            for (Task task : tasks) {
                taskStrings.add(task.toFileString());
            }
            return taskStrings;
        }
    }

    private class CommandProcessor {
        private final String command;
        private boolean isExitCommand;

        public CommandProcessor(String command) {
            this.command = command;
        }

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
                    default:
                        ui.showUnknownCommandError();
                }
            }
        }

        private void processTodoCommand(TaskList tasks, Ui ui) {
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

        private void processDeadlineCommand(TaskList tasks, Ui ui) {
            try {
                String descriptionWithDate = command.substring("deadline".length()).trim();
                DeadlineTask ddlTask = new DeadlineTask(descriptionWithDate, "by");
                tasks.addTask(ddlTask.createTask(descriptionWithDate));
                ui.showTaskAddedMessage(tasks.getAllTasks());
            } catch (RochinException e) {
                ui.showError(e.getMessage());
            }
        }

        private void processEventCommand(TaskList tasks, Ui ui) {
            try {
                String descriptionWithDate = command.substring("event".length()).trim();
                EventTask eventTask = new EventTask(descriptionWithDate, "from", "to");
                tasks.addTask(eventTask.createTask(descriptionWithDate));
                ui.showTaskAddedMessage(tasks.getAllTasks());
            } catch (RochinException e) {
                ui.showError(e.getMessage());
            }
        }

        private void processDeleteCommand(TaskList tasks, Ui ui) {
            int taskIndex = getTaskIndex();
            tasks.deleteTask(taskIndex);
            ui.showTaskDeletedMessage(tasks.getAllTasks());
        }

        private void processMarkCommand(TaskList tasks, Ui ui) {
            int taskIndex = getTaskIndex();
            tasks.markTaskAsDone(taskIndex);
            ui.showTaskMarkedAsDoneMessage(tasks.getAllTasks());
        }

        private void processUnmarkCommand(TaskList tasks, Ui ui) {
            int taskIndex = getTaskIndex();
            tasks.unmarkTaskAsDone(taskIndex);
            ui.showTaskUnmarkedAsDoneMessage(tasks.getAllTasks());
        }

        private int getTaskIndex() {
            try {
                String[] splitCommand = command.split("\\s+");
                return Integer.parseInt(splitCommand[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.showInvalidCommandError();
                return -1;
            }
        }

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
        public Task createTaskFromFileString(String fileLine) throws RochinException {
            String[] parts = fileLine.split("\\s*\\|\\s*");
            if (parts.length >= 3) {
                boolean isDone = Integer.parseInt(parts[1]) == 1;
                String description = parts[2];
                // Extract additional details based on task type (Todo, Deadline, Event)
                String additionalDetails = parts.length > 3 ? parts[3] : null;
                if (parts[0].equals("T")) {
                    TodoTask todoTask = new TodoTask(description);
                    if (isDone) {
                        todoTask.markAsDone();
                    }
                    return todoTask;
                } else if (parts[0].equals("D")) {
                    DeadlineTask deadlineTask = new DeadlineTask(description, additionalDetails);
                    if (isDone) {
                        deadlineTask.markAsDone();
                    }
                    return deadlineTask;
                } else if (parts[0].equals("E")) {
                    description = parts[2] + parts[3];
                    EventTask eventTask = new EventTask(description,"from", "to");
                    eventTask.createTask(description);
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
        public DeadlineTask createTask(String descriptionWithDate) throws RochinException {
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
        public EventTask createTask(String descriptionWithDate) throws RochinException {
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
    static class RochinException extends Exception {

        /**
         * Constructs an exception with the specified error message.
         *
         * @param message The error message.
         */
        public RochinException(String message) {
            super(message);
        }
    }
}
