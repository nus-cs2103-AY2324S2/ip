package com.example.artemis;

import java.util.ArrayList;

/**
 * Represents the user interface for the Artemis application.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     *
     * @return A formatted welcome message.
     */
    public String showWelcomeMessage() {
        return showLine()
                + "     Hello! I'm Artemis\n"
                + "     What can I do for you?\n"
                + showLine();
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return A formatted goodbye message.
     */
    public String showGoodbyeMessage() {
        return showLine()
                + "     Bye. Hope to see you again soon!\n"
                + showLine();
    }

    /**
     * Displays an error message related to loading tasks.
     *
     * @return A formatted error message for loading tasks.
     */
    public String showLoadingError() {
        return showLine()
                + "     Error loading tasks from file.\n"
                + showLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     * @return A formatted error message.
     */
    public String showError(String message) {
        return showLine()
                + "     " + message + "\n"
                + showLine();
    }

    /**
     * Displays all the tasks in the list.
     *
     * @param tasks The task list that contains all the tasks.
     * @return A formatted string displaying all tasks.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(showLine()).append("     Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            result.append("     ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        result.append(showLine());
        return result.toString();
    }

    /**
     * Handles the task finding operation by searching for tasks with descriptions
     * containing the specified command. Displays the matching tasks and their
     * corresponding numbers in the original task list.
     *
     * @param tasks   The list of tasks to search within.
     * @param command The keyword to search for in task descriptions.
     * @return A formatted string displaying the matching tasks.
     */
    public String handleFindTask(ArrayList<Task> tasks, String command) {
        int count = 1;
        StringBuilder result = new StringBuilder();
        result.append(showLine()).append("     Here are the matching tasks in your list:\n");
        for (Task task : tasks) {
            if (task.description.contains(command)) {
                result.append("     ").append(count).append(".").append(task).append("\n");
                count++;
            }
        }
        result.append(showLine());
        return result.toString();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A formatted message for marking a task as done.
     */
    public String showTaskMarkedAsDone(Task task) {
        return showLine()
                + "     Nice! I've marked this task as done:\n"
                + "       " + task + "\n"
                + showLine();
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A formatted message for marking a task as not done.
     */
    public String showTaskMarkedAsNotDone(Task task) {
        return showLine()
                + "     OK, I've marked this task as not done yet:\n"
                + "       " + task + "\n"
                + showLine();
    }

    /**
     * Displays a message indicating a new task has been added.
     *
     * @param size The total number of tasks after the addition.
     * @param task The task that has been added.
     * @return A formatted message for adding a new task.
     */
    public String showTaskAdded(int size, Task task) {
        return showLine()
                + "     Got it. I've added this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + size + " tasks in the list.\n"
                + showLine();
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task  The task that has been deleted.
     * @param index The total number of tasks after the deletion.
     * @return A formatted message for deleting a task.
     */
    public String showTaskDelete(Task task, int index) {
        return showLine()
                + "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + index + " tasks in the list.\n"
                + showLine();
    }

    /**
     * Displays a horizontal line separator.
     *
     * @return A string representing a horizontal line separator.
     */
    public String showLine() {
        return "    _________________________________________________________\n";
    }
}
