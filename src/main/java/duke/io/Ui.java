package duke.io;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui - Handles interactions with the user, including input and output.
 */
public class Ui {

    /**
     * Generates the welcome message.
     *
     * @return The welcome message as a String.
     */
    public static String showWelcomeMessage() {
        return "Hello! I'm RATZCHAT\n" +
                "How can I help you today?\n";
    }

    /**
     * Generates the goodbye message.
     *
     * @return The goodbye message as a String.
     */
    public static String showByeMessage() {
        return "BYEBYE. Thank you for using RATZCHAT!\n";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public static String showErrorMessage(String message) {
        return "Error: " + message + "':\n";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks ArrayList of tasks to be displayed.
     * @return The user's input as a String.
     */
    public static String showTaskList(ArrayList<Task> tasks) {
        StringBuilder taskListString = new StringBuilder();
        taskListString.append("These are your to-dos: ");
        for (int i = 0; i < tasks.size(); i++) {
            taskListString.append("\n").append((i + 1)).append(".").append(tasks.get(i));
        }
        taskListString.append("\n");
        return taskListString.toString();
    }

    /**
     * Generates a message indicating a todo task has been added.
     *
     * @param todo The todo task that was added.
     * @param totalTasks The total number of tasks in the list after adding the todo.
     * @return A formatted message as a String.
     */
    public static String showTodoAdded(Todo todo, int totalTasks) {
        String response = "Ok! I've added this todo: " + todo + "\n"
                + "Now you have " + totalTasks + " tasks in your list."
                + "\n";
        return response;
    }

    /**
     * Generates a message indicating a deadline task has been added.
     *
     * @param deadline The deadline task that was added.
     * @param totalTasks The total number of tasks in the list after adding the deadline.
     * @return A formatted message as a String.
     */
    public static String showDeadlineAdded(Deadline deadline, int totalTasks) {
        return "Ok! I've added this deadline: " + deadline + "\n" +
                "Now you have " + totalTasks + " tasks in your list.\n";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return String representation of the marked task.
     */
    public static String showMarkedAsDone(Task task) {
        return "I've marked this task as done:\n  " + task + "\n";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been unmarked.
     * @return String representation of the unmarked task.
     */
    public static String showUnmarkedTask(Task task) {
        return "I've unmarked this task! It is now not done yet:\n  " + task + "\n";
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks after the addition.
     * @return String representation of the added task.
     */
    public static String showTaskAdded(Task task, int totalTasks) {
        return "Ok! I've added this task: " + task + "\nNow you have " + totalTasks + " tasks in your list.\n";
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param removedTask The task that has been removed.
     * @param totalTasks  The total number of tasks after the removal.
     * @return String representation of the removed task.
     */
    public static String showTaskRemoved(Task removedTask, int totalTasks) {
        return "Ok! I have removed this task from your list:\n  " + removedTask + "\nNow you have " + totalTasks + " tasks in your list.\n";
    }

    /**
     * Displays a message indicating the list of items found with the keyword.
     *
     * @param keyword The keyword that was searched.
     * @return String representation of the found tasks.
     */
    public static String showFindItemList(String keyword) {
        return "Items containing '" + keyword + ":\n";
    }
}