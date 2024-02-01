package checkbot;

import checkbot.exception.CheckbotException;
import checkbot.task.Task;
import checkbot.task.TodoList;

/**
 * Handles printing of messages to the user.
 */
public class Ui {
    public static final String INDENTATION = "  ";
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        print(
                "Hello, I'm Checkbot, your personal assistant.\n" +
                        "What tasks do you have to do?");
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void showGoodbye() {
        print("Goodbye!");
    }

    public void showList(TodoList todoList) {
        print("Here is your todo list:\n"
                + todoList);
    }

    /**
     * Shows the message to the user when a task is added to the list.
     * 
     * @param task   The task that was added.
     * @param length The number of tasks in the list.
     */
    public void showAddedTaskMessage(Task task, int length) {
        print("I have added this task to the list:\n"
                + INDENTATION + task + "\n"
                + "You have now " + length + " task"
                + (length > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows the message to the user when a task is marked as done.
     * 
     * @param task The task that was marked as done.
     */
    public void showMarkedTaskMessage(Task task) {
        print("Good job! I have marked this task as completed:\n"
                + INDENTATION + task);
    }

    /**
     * Shows the message to the user when a task is marked as incomplete.
     * 
     * @param task The task that was marked as incomplete.
     */
    public void showUnmarkedTaskMessage(Task task) {
        print("Alright, I have marked this task as incomplete:\n"
                + INDENTATION + task);
    }

    /**
     * Shows the message to the user when a task is deleted from the list.
     * 
     * @param task   The task that was deleted.
     * @param length The number of tasks in the list.
     */
    public void showDeletedTaskMessage(Task task, int length) {
        print("Alright, I deleted this task:\n"
                + INDENTATION + task + "\n"
                + "You have now " + length + " task"
                + (length > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows the error message to the user.
     * 
     * @param e The exception that was thrown.
     */
    public void showError(CheckbotException e) {
        print(e.getMessage());
    }

    /**
     * Prints the given string to the console. Starts and ends with a separator
     * and indents each line with the indentation string.
     *
     * @param str The string to be printed.
     */
    private void print(String str) {
        System.out.println(INDENTATION + SEPARATOR);
        for (String chunk : str.split("\n")) {
            System.out.println(INDENTATION + chunk);
        }
        System.out.println(INDENTATION + SEPARATOR);
    }
}
