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
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Yapper.");
    }
    /**
     * Displays instructions to guide the user.
     */
    public void showInstructions() {
        System.out.println("What would you like to yap about today? :-)");
    }
    /**
     * Displays a user prompt.
     */
    public void showUserPrompt() {
        System.out.println("User: ");
    }
    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Creating a new task list.");
    }
    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your yapping list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
    /**
     * Displays a message after marking a task as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedDoneMessage(Task task) {
        System.out.println("Nice yap! I've marked this task as done:");
        System.out.println(" " + task);
    }
    /**
     * Displays a message after marking a task as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showMarkedNotDoneMessage(Task task) {
        System.out.println("Ok bro, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }
    /**
     * Displays a message after adding a new task.
     *
     * @param task      The task that has been added.
     * @param taskCount The total count of tasks after adding the new task.
     */
    public void showAddedTaskMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + taskCount
                + " tasks in the list.");
    }
    /**
     * Displays a message after removing a task.
     *
     * @param task      The task that has been removed.
     * @param taskCount The total count of tasks after removing the task.
     */
    public void showRemovedTaskMessage(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + taskCount
                + " tasks in the list.");
    }
    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to yap with you again soon!");
    }
    /**
     * Displays matching tasks based on a keyword search.
     *
     * @param matchingTasks The list of matching tasks.
     * @param keyword       The keyword used for searching.
     */
    public void showMatchingTasks(List<Task> matchingTasks, String keyword) {
        System.out.println("Here are the matching tasks for keyword '" + keyword + "':");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }
}