package james.ui;

import java.util.List;

import james.exception.DukeException;
import james.tasklist.TaskList;
import james.tasks.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        //empty constructor
    }

    /**
     * Shows the welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm James!\nWhat can I do for you?";
    }

    /**
     * Shows the goodbye message to the user.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Shows the error message to the user.
     *
     * @param message The error message to be shown.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows the task to the user.
     *
     * @param message The message to be shown.
     */
    public String showTask(String message, Task task, int size) {
        return message + "\n" + task + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Shows the task list to the user.
     *
     * @param tasks The task list to be shown.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
            } catch (DukeException e) {
                sb.append("Error: ").append(e.getMessage()).append("\n");
            }
        }
        return sb.toString().trim();
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param tasks The tasks found to be shown.
     */
    public String showTasksFound(List<String> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks match your search keyword.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param task The tasks found to be shown.
     * @param remainingTasks The number of remaining tasks.
     */
    public String showDeletedTask(Task task, int remainingTasks) {
        return "Noted. I've removed this task: \n" + task + "\nNow you have " + remainingTasks + " tasks in the list.";
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param task The tasks found to be shown.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param task The tasks found to be shown.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
