package ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import task.Task;

/**
 * Represents the user interface of the application.
 * <p>
 * This class is used to display messages to the user, such as welcome and
 * goodbye messages, error messages, and messages related to tasks.
 * </p>
 */
public class Ui {

    private static final Map<String, String> commandDescriptions = new LinkedHashMap<>();

    static {
        commandDescriptions.put("todo", "Add a todo task: todo <description>");
        commandDescriptions.put("deadline", "Add a deadline task: deadline <description> /by <date>");
        commandDescriptions.put("event", "Add an event task: event <description> /at <date>");
        commandDescriptions.put("list", "List all tasks: list");
        commandDescriptions.put("mark", "Mark a task as done: mark <taskNumber>");
        commandDescriptions.put("unmark", "Mark a task as not done: unmark <taskNumber>");
        commandDescriptions.put("delete", "Remove a task: delete <taskNumber>");
        commandDescriptions.put("find", "Find tasks by keyword: find <keyword>");
        commandDescriptions.put("tag", "Tag a task: tag <taskNumber> <tag>");
        commandDescriptions.put("bye", "Exit the application: bye");
    }

    /**
     * Displays the welcome message to the user.
     * 
     * @return The welcome message
     */
    public String getWelcomeMessage() {
        String botName = "GeePeeTee";
        return "Hello! I'm " + botName + "\nWhat can I do for you?";
    }

    /**
     * Displays the goodbye message to the user.
     * 
     * @return The goodbye message
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message when loading the task list file.
     * 
     * @return The loading error message
     */
    public String getLoadingErrorMessage() {
        return "Error loading the task list file.";
    }

    /**
     * Displays an error message when the task list file is not found.
     * 
     * @return The file not found error message
     */
    public String getFileNotFoundErrorMessage() {
        return "File not found.";
    }

    /**
     * Displays an error message to the user.
     * 
     * @param message The error message to be displayed
     * @return The error message
     */
    public String getErrorMessage(String message) {
        return "Oops! An error occurred:\n" + message;
    }

    /**
     * Displays the task to the user.
     * 
     * @param task The task to be displayed
     * @return The task message
     */
    public String getTaskMessage(Task task) {
        return task.toString();
    }

    /**
     * Displays the unmarking of a task as undone to the user.
     * 
     * @param task The task to be unmarked as undone
     * @return The task unmarked message
     */
    public String getTaskUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + getTaskMessage(task);
    }

    /**
     * Displays the marking of a task as done to the user.
     * 
     * @param task The task to be marked as done
     * @return The task marked message
     */
    public String getTaskMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + getTaskMessage(task);
    }

    /**
     * Displays the addition of a task to the user.
     * 
     * @param task      The task to be added
     * @param taskCount The number of tasks in the list
     */
    public String getAddTaskMessage(Task task, int taskCount) {
        return "Got it. I've added this task:\n" + getTaskMessage(task) + "\n" + getTaskCountMessage(taskCount);
    }

    /**
     * Displays the deletion of a task to the user.
     * 
     * @param task      The task to be deleted
     * @param taskCount The number of tasks in the list
     * @return The task deleted message
     */
    public String getDeleteTaskMessage(Task task, int taskCount) {
        return "Noted. I've removed this task:\n" + getTaskMessage(task) + "\n" + getTaskCountMessage(taskCount);
    }

    /**
     * Displays the list of commands available to the user.
     */
    public String getListOfCommandsMessage() {
        return "Here are the available commands:\n" + commandDescriptions.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Displays specific command to the user.
     * 
     * @param command The command to be displayed
     */
    public String getCommandDescriptionMessage(String command) {
        return "\n\n" + "Format: " + commandDescriptions.get(command);
    }

    /**
     * Displays the list of keyword matching tasks to the user.
     * 
     * @param matchingTasks The list of tasks matching the keyword
     */
    public String getMatchingTasksMessage(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "There are no matching tasks in your list.";
        }
        String matchingTasksOutput = matchingTasks.stream()
                .map(task -> (matchingTasks.indexOf(task) + 1) + ". " + task + "\n")
                .collect(Collectors.joining());
        return "Here are the matching tasks in your list:\n" + matchingTasksOutput;
    }

    public String getTagTaskMessage(Task task) {
        return "Got it. I have tagged this task:\n" + getTaskMessage(task);
    }

    /**
     * Displays the number of tasks remaining in the task list.
     * 
     * @param taskCount The number of tasks in the list
     * @return The string representation of the number of remaining tasks
     */
    private String getTaskCountMessage(int taskCount) {
        if (taskCount == 1) {
            return "Now you have " + taskCount + " task in the list.";
        } else {
            return "Now you have " + taskCount + " tasks in the list.";
        }
    }
}
