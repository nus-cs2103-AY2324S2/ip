package unim.io;
import unim.task.Deadline;
import unim.task.Task;
import unim.task.Todo;

import java.util.ArrayList;

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
        return "Hey there, welcome back to Unim!\n" +
                "What's on your mind today?\n";
    }

    /**
     * Generates the goodbye message.
     *
     * @return The goodbye message as a String.
     */
    public static String showByeMessage() {
        return "Leaving ALREADY? BYEBYE :( \n Remember, I'm just a message away. \n";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public static String showErrorMessage(String message) {
        assert message != null : "Error message should not be null";
        return "Whoops! " + message + "\n";
    }


    /**
     * Displays an error message for invalid inputs.
     */
    public static String showErrorForInput() {
        String message = "I'm sorry, I don't understand! Please type your request again. \n " +
                "You can choose to: \n 1. add tasks (todo, deadline, event) \n 2. find tasks \n " +
                "3. mark or unamrk tasks \n 4. delete tasks";
        return message;
    }

    /**
     * Displays the list of tasks    .
     *
     * @param tasks ArrayList of tasks to be displayed.
     * @return The user's input as a String.
     */
    public static String showTaskList(ArrayList<Task> tasks) {
        StringBuilder taskListString = new StringBuilder();
        if (tasks.isEmpty()) {
            taskListString.append("No tasks in your list!\n");
        } else {
            taskListString.append("Here are your to-dos: ");
            for (int i = 0; i < tasks.size(); i++) {
                taskListString.append("\n").append((i + 1)).append(".").append(tasks.get(i).toString());
            }
            taskListString.append("\n");
        }
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
        String response = "Your to-do: " + todo + " is on the list! You're gonna nail it :)\n"
                + "Just " + totalTasks + " tasks in your list now."
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
        return "New deadline alert!\n " + deadline + "!\n" +
                "Now you have " + totalTasks + " tasks in your list.\n";
    }


    /**
     * Displays a message indicating the tasks that are found.
     *
     * @param task The tasks to be displayed in the list.
     */
    public static String showFoundTask(Task task) {
        return task.toString() + "\n";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return String representation of the marked task.
     */
    public static String showMarkedAsDone(Task task) {
        return "Boom! " + task + " is marked done.\n" +
                "You're crushing it!! What's next?";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been unmarked.
     * @return String representation of the unmarked task.
     */
    public static String showUnmarkedTask(Task task) {
        return "Not done yet? Time is ticking!! " +
                "Try to finish this task soon: \n" + task + "\n";
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks after the addition.
     * @return String representation of the added task.
     */
    public static String showTaskAdded(Task task, int totalTasks) {
        return "Ok! " + task + "is on the list!\n" +
                "Now you have " + totalTasks + " tasks in your list.\n";
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param removedTask The task that has been removed.
     * @param totalTasks  The total number of tasks after the removal.
     * @return String representation of the removed task.
     */
    public static String showTaskRemoved(Task removedTask, int totalTasks) {
        return "Poof! This task has been removed from your list:\n  " + removedTask +
                "\nNow you have " + totalTasks + " tasks in your list.\n";
    }

    /**
     * Displays a message indicating the list of items found with the keyword.
     *
     * @param keyword The keyword that was searched.
     * @return String representation of the found tasks.
     */
    public static String showFindItemList(String keyword) {
        assert keyword != null : "Keyword should not be null";
        return "Here are the tasks with " + keyword + " in them: \n" +
                "You're going to nail this! ";
    }
}