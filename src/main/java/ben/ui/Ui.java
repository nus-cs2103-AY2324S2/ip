package ben.ui;

import ben.tasks.Task;
import ben.tasks.TaskList;

/**
 * A class representing the user interface for task management.
 */
public class Ui {

    public static final String ERROR_MESSAGE = "ERROR: ";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String MARKED_TASK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARKED_TASK_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String TASK_SIZE_PRE_MESSAGE = "Now you have ";
    public static final String TASK_SIZE_POST_MESSAGE = " tasks in the list.";
    public static final String DELETED_TASK_MESSAGE = "Noted. I've removed this task:";
    public static final String TASKS_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String NO_TASKS_FOUND_MESSAGE = "Sorry, there are no matching tasks... Try another keyword!";

    /**
     * Concatenates an array of messages into a single string with line breaks.
     *
     * @param messages The messages to be concatenated.
     * @return A string containing the concatenated messages with line breaks.
     */
    public String show(String... messages) {
        StringBuilder output = new StringBuilder();
        for (String message : messages) {
            output.append(message)
                    .append("\n");
        }
        return output.toString();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     * @return A formatted error message.
     */
    public String showError(String message) {
        return show(ERROR_MESSAGE,
                message);
    }

    /**
     * Displays the exit message.
     *
     * @return The exit message.
     */
    public String showExit() {
        return show(EXIT_MESSAGE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list to be displayed.
     * @return A formatted message with the list of tasks.
     */
    public String showList(TaskList tasks) {
        return show(LIST_MESSAGE,
                showTaskList(tasks));
    }

    /**
     * Displays a single task.
     *
     * @param task The task to be displayed.
     * @return A formatted message with the task.
     */
    public String showTask(Task task) {
        return show(task.toString());
    }

    /**
     * Displays a specific task from the task list.
     *
     * @param tasks The task list containing the task to be displayed.
     * @param index The index of the task to be displayed.
     * @return A formatted message with the specified task.
     */
    public String showTask(TaskList tasks, int index) {
        return show(tasks.toString(index));
    }

    /**
     * Displays the entire task list.
     *
     * @param tasks The task list to be displayed.
     * @return A formatted message with the entire task list.
     */
    public String showTaskList(TaskList tasks) {
        return tasks.showTaskList();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks The task list containing the marked task.
     * @param index The index of the marked task.
     * @return A formatted message indicating the marked task.
     */
    public String showMarkedTask(TaskList tasks, int index) {
        return show(MARKED_TASK_MESSAGE,
                showTask(tasks, index));
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param tasks The task list containing the unmarked task.
     * @param index The index of the unmarked task.
     * @return A formatted message indicating the unmarked task.
     */
    public String showUnmarkedTask(TaskList tasks, int index) {
        return show(UNMARKED_TASK_MESSAGE,
                showTask(tasks, index));
    }

    /**
     * Displays a message indicating the current number of tasks in the list.
     *
     * @param tasks The task list.
     * @return A formatted message indicating the number of tasks.
     */
    public String showTasksSize(TaskList tasks) {
        return show(TASK_SIZE_PRE_MESSAGE +
                tasks.size() +
                TASK_SIZE_POST_MESSAGE);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param tasks The task list containing the added task.
     * @param task  The added task.
     * @return A formatted message indicating the added task and the current number of tasks.
     */
    public String showAddedTask(TaskList tasks, Task task) {
        return show(ADDED_TASK_MESSAGE,
                showTask(task),
                showTasksSize(tasks));
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The deleted task.
     * @return A formatted message indicating the deleted task.
     */
    public String showDeletedTask(Task task) {
        return show(DELETED_TASK_MESSAGE,
                showTask(task));
    }

    /**
     * Displays a message indicating the tasks that match a search criterion.
     *
     * @param tasks The task list containing the matching tasks.
     * @return A formatted message indicating the matching tasks.
     */
    public String showTasksFound(TaskList tasks) {
        return show(TASKS_FOUND_MESSAGE,
                tasks.showTaskList());
    }

    /**
     * Displays a message when no matching tasks are found.
     *
     * @return A message indicating that no matching tasks were found.
     */
    public String showNoTasksFound() {
        return show(NO_TASKS_FOUND_MESSAGE);
    }
}
