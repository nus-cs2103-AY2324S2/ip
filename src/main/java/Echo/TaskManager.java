/*
 * Package: Echo
 * Module/Project Name: Echo
 * File: TaskManager.java
 *
 * Description:
 * This class manages tasks, handling operations such as adding, listing, marking, unmarking, and deleting tasks.
 * It interacts with the Storage class to save and load tasks from a file.
 *
 */

package Echo;

import Echo.Task.Task;
import Echo.Task.Todo;
import Echo.Task.Deadline;
import Echo.Task.Event;
import Echo.Storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private List<Task> tasks;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "echo.txt";
    private Storage storage;

    /**
     * Constructor for the TaskManager class.
     *
     * @param storage The Storage object to handle file operations.
     */
    public TaskManager(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        loadTasksFromFile();
    }

    /**
     * Saves tasks to the file using the Storage class.
     */
    private void saveTasksToFile() {
        storage.save(tasks);
    }

    /**
     * Loads tasks from the file using the Storage class.
     */
    private void loadTasksFromFile() {
        tasks = storage.load();
    }

    /**
     * Executes a command based on user input.
     *
     * @param command The user input command.
     */
    public void executeCommand(String command) {
        String[] tokens = command.split(" ", 2);

        switch (tokens[0].toLowerCase()) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTask(tokens);
                break;
            case "unmark":
                unmarkTask(tokens);
                break;
            case "delete":
                deleteTask(tokens);
                break;
            case "find":
                if (tokens.length == 2) {
                    findTasks(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: find <keyword>");
                }
                break;
            case "bye":
                break;
            default:
                addTask(tokens);
        }
    }

    /**
     * Lists all tasks in the console.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void markTask(String[] tokens) {
        if (tokens.length == 2) {
            int index = Integer.parseInt(tokens[1]);
            if (isValidIndex(index)) {
                tasks.get(index - 1).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index - 1));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Invalid command. Usage: mark <index>");
        }
        saveTasksToFile();
    }

    /**
     * Marks a task as undone based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void unmarkTask(String[] tokens) {
        if (tokens.length == 2) {
            int index = Integer.parseInt(tokens[1]);
            if (isValidIndex(index)) {
                tasks.get(index - 1).markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(index - 1));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Invalid command. Usage: unmark <index>");
        }
        saveTasksToFile();
    }

    /**
     * Adds a task based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void addTask(String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new IllegalArgumentException("NO! I don't know what is this! Invalid command. Supported tasks: todo, deadline, event");
            }
            String[] taskTokens = tokens;

            String taskType = taskTokens[0].toLowerCase();
            if (taskTokens[1].isEmpty()) {
                throw new IllegalArgumentException("NO! The description of a task cannot be empty.");
            }
            String taskDescription = taskTokens[1].trim();

            switch (taskType) {
                case "todo":
                    if (taskDescription.isEmpty()) {
                        throw new IllegalArgumentException("NO! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(taskDescription));
                    break;
                case "deadline":
                    String[] deadlineTokens = taskDescription.split(" /by ", 2);
                    if (deadlineTokens.length != 2) {
                        throw new IllegalArgumentException("NO! Invalid command. Enter: deadline <description> /by <date/time>");
                    }
                    tasks.add(new Deadline(deadlineTokens[0], deadlineTokens[1]));
                    break;
                case "event":
                    String[] eventTokens = taskDescription.split(" /from ", 2);
                    if (eventTokens.length != 2) {
                        throw new IllegalArgumentException("NO! Invalid command. Enter: event <description> /from <start> /to <end>");
                    }
                    String[] toTokens = eventTokens[1].split(" /to ", 2);
                    if (toTokens.length != 2) {
                        throw new IllegalArgumentException("NO! Invalid command. Enter: event <description> /from <start> /to <end>");
                    }
                    tasks.add(new Event(eventTokens[0], toTokens[0], toTokens[1]));
                    break;
                default:
                    throw new IllegalArgumentException("No! I don't what what is this! Invalid task type. Supported types: todo, deadline, event");
            }

            printTaskAddedMessage(tasks.size());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        saveTasksToFile();
    }

    /**
     * Checks if the given index is valid for the tasks list.
     *
     * @param index The index to check.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    /**
     * Prints a message indicating the successful addition of a task.
     *
     * @param size The size of the tasks list after the addition.
     */
    private void printTaskAddedMessage(int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(size - 1));
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Deletes a task based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void deleteTask(String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new IllegalArgumentException("NO! Invalid command. Enter: delete <task number>");
            }

            int taskNumber = Integer.parseInt(tokens[1]) - 1;  // Adjusting for 0-based index
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new IllegalArgumentException("NO! Echo.Task.Echo.Task number does not exist. Enter a valid task number to delete.");
            }

            Task removedTask = tasks.remove(taskNumber);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("NO! Invalid task number. Enter a valid task number to delete.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        saveTasksToFile();
    }

    /**
     * Deletes all tasks and resets the file.
     */
    public void deleteAllTasks() {
        tasks.removeAll(tasks);
        resetFile();
    }

    /**
     * Resets the file by clearing its content.
     */
    public void resetFile() {
        try {
            File file = new File(FILE_PATH);

            // Create a new file writer with append mode set to false (clears existing content)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                // Clear the tasks list
                tasks.clear();
                System.out.println("Tasks list cleared.");

                System.out.println("File content cleared successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error resetting file: " + e.getMessage());
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks containing the specified keyword in their descriptions.
     * Case-insensitive matching is performed.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        System.out.println("____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

}