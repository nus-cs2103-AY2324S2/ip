package yapper;

import java.util.List;

import yapper.tasks.Task;

/**
 * The Ui class handles user interface-related functions, such as displaying messages.
 */
public class Ui {
    /**
     * Displays a welcome message when the application starts.
     */
    public String showWelcomeMessage() {
        String message = "Hello! I'm Yapper. /n"
                + "What would you like to yap about today? :-)";
        System.out.println(message);
        assert message != null : "Welcome message should not be null";
        return message;
    }
    /**
     * Displays a user prompt.
     */
    public String showUserPrompt() {
        String message = "User: ";
        System.out.println(message);
        assert message != null : "User prompt should not be null";
        return message;
    }
    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public String showError(String errorMessage) {
        String message = errorMessage;
        System.out.println(message);
        assert message != null : "Error message should not be null";
        return message;
    }
    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public String showLoadingError() {
        String message = "Error loading tasks from file. Creating a new task list.";
        System.out.println(message);
        assert message != null : "Loading error message should not be null";
        return message;
    }
    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showTaskList(List<Task> tasks) {
        StringBuilder result = new StringBuilder("Here are the tasks in your yapping list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        String message = result.toString();
        System.out.println(message);
        assert message != null : "Task list message should not be null";
        return message;
    }
    /**
     * Displays a message after marking a task as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkedDoneMessage(Task task) {
        String message = "Nice yap! I've marked this task as done:\n " + task;
        System.out.println(message);
        return message;
    }
    /**
     * Displays a message after marking a task as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showMarkedNotDoneMessage(Task task) {
        String message = "Ok bro, I've marked this task as not done yet:\n " + task;
        System.out.println(message);
        return message;
    }
    /**
     * Displays a message after adding a new task.
     *
     * @param task      The task that has been added.
     * @param taskCount The total count of tasks after adding the new task.
     */
    public String showAddedTaskMessage(Task task, int taskCount) {
        String message = "Got it. I've added this task:\n   " + task + "\nNow you have " + taskCount
                + " tasks in your yapping list.";
        System.out.println(message);
        return message;
    }
    /**
     * Displays a message after removing a task.
     *
     * @param task      The task that has been removed.
     * @param taskCount The total count of tasks after removing the task.
     */
    public String showRemovedTaskMessage(Task task, int taskCount) {
        String message = "Noted. I've removed this task:\n   " + task + "\nNow you have " + taskCount
                + " tasks in your yapping list.";
        System.out.println(message);
        return message;
    }
    /**
     * Displays a goodbye message when the user exits the application.
     */
    public String showGoodbyeMessage() {
        String message = "Bye. Hope to yap with you again soon!";
        System.out.println(message);
        return message;
    }
    /**
     * Displays matching tasks based on a keyword search.
     *
     * @param matchingTasks The list of matching tasks.
     * @param keyword       The keyword used for searching.
     */
    public String showMatchingTasks(List<Task> matchingTasks, String keyword) {
        StringBuilder result = new StringBuilder("Here are the matching tasks for keyword '" + keyword + "':\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1)).append(".").append(matchingTasks.get(i)).append("\n");
        }
        String message = result.toString();
        System.out.println(message);
        return message;
    }
}