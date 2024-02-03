package ui;

import java.util.ArrayList;

import task.Task;

/**
 * Represents the user interface of the application.
 * <p>
 * This class is used to display messages to the user, such as welcome and
 * goodbye messages, error messages, and messages related to tasks.
 * </p>
 */
public class Ui {

    /*
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        String botName = "GeePeeTee";
        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?");
    }

    /*
     * Displays the goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /*
     * Displays an error message when loading the task list file.
     */
    public void showLoadingError() {
        System.out.println("Error loading the task list file.");
    }

    /*
     * Displays an error message when the task list file is not found.
     */
    public void showFileNotFoundError() {
        System.out.println("File not found.");
    }

    /*
     * Displays an error message to the user.
     * 
     * @param message The error message to be displayed
     */
    public void showErrorMessage(String message) {
        System.out.println("Oops! An error occurred:");
        System.out.println(message);
    }

    /*
     * Displays the task to the user.
     * 
     * @param task The task to be displayed
     */
    public void showTaskMessage(Task task) {
        System.out.println(task);
    }

    /*
     * Displays the unmarking of a task as undone to the user.
     * 
     * @param task The task to be unmarked as undone
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        showTaskMessage(task);
    }

    /*
     * Displays the marking of a task as done to the user.
     * 
     * @param task The task to be marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        showTaskMessage(task);
    }

    /*
     * Displays the addition of a task to the user.
     * 
     * @param task The task to be added
     * 
     * @param taskCount The number of tasks in the list
     */
    public void showAddTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        showTaskMessage(task);
        System.out.println(getTaskCountMessage(taskCount));
    }

    /*
     * Displays the deletion of a task to the user.
     * 
     * @param task The task to be deleted
     * 
     * @param taskCount The number of tasks in the list
     */
    public void showDeleteTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        showTaskMessage(task);
        System.out.println(getTaskCountMessage(taskCount));
    }

    /*
     * Displays the list of commands available to the user.
     */
    public void showListOfCommands() {
        System.out.println("Here are the available commands:");
        System.out.println("1. todo <description>: Add a todo task");
        System.out.println("2. deadline <description> /by <date>: Add a deadline task");
        System.out.println("3. event <description> /at <date>: Add an event task");
        System.out.println("4. list: List all tasks");
        System.out.println("5. mark <taskNumber>: Mark a task as done");
        System.out.println("6. unmark <taskNumber>: Mark a task as not done");
        System.out.println("7. delete <taskNumber>: Remove a task");
        System.out.println("8. bye: Exit the program");
    }

    /*
     * Returns the string representation of the number of tasks remaining in the
     * task list.
     * 
     * @param taskCount The number of tasks in the list
     * 
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
