package com.example.artemis;

import java.util.ArrayList;

/**
 * Represents the user interface for the Artemis application.
 */
public class Ui {

    public static final String DIVIDER_LINE = "    _______________________________________________    \n";
    public static final String HELLO_I_M_ARTEMIS = "     Hello! I'm Artemis\n";
    public static final String WHAT_CAN_I_DO_FOR_YOU = "     What can I do for you?\n";
    public static final String SEE_YOU_AGAIN_SOON = "     Bye. Hope to see you again soon!\n";
    public static final String ERROR_LOADING_TASKS_FROM_FILE = "     Error loading tasks from file.\n";
    public static final String INDENT = "     ";
    public static final String LINE_BREAK = "\n";
    public static final String SHOW_TASK = "     Here are the tasks in your list:\n";
    public static final String MATCHING_TASK = "     Here are the matching tasks in your list:\n";
    public static final String MARK_DONE = "     Nice! I've marked this task as done:\n";
    public static final String MARK_NOT_DONE = "     OK, I've marked this task as not done yet:\n";
    public static final String ADD_TASK = "     Got it. I've added this task:\n";
    public static final String HAVE = "     Now you have ";
    public static final String TASKS = " tasks in the list.\n";
    public static final String REMOVE_TASK = "     Noted. I've removed this task:\n";

    /**
     * Displays a welcome message to the user.
     *
     * @return A formatted welcome message.
     */
    public String showWelcomeMessage() {
        return DIVIDER_LINE
                + HELLO_I_M_ARTEMIS
                + WHAT_CAN_I_DO_FOR_YOU
                + DIVIDER_LINE;
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return A formatted goodbye message.
     */
    public String showGoodbyeMessage() {
        return DIVIDER_LINE
                + SEE_YOU_AGAIN_SOON
                + DIVIDER_LINE;
    }

    /**
     * Displays an error message related to loading tasks.
     *
     * @return A formatted error message for loading tasks.
     */
    public String showLoadingError() {
        return DIVIDER_LINE
                + ERROR_LOADING_TASKS_FROM_FILE
                + DIVIDER_LINE;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     * @return A formatted error message.
     */
    public String showError(String message) {
        return DIVIDER_LINE
                + INDENT + message + LINE_BREAK
                + DIVIDER_LINE;
    }

    /**
     * Displays all the tasks in the list.
     *
     * @param tasks The task list that contains all the tasks.
     * @return A formatted string displaying all tasks.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        result.append(DIVIDER_LINE).append(SHOW_TASK);
        for (int i = 0; i < tasks.size(); ++i) {
            result.append(INDENT).append(i + 1).append(".").append(tasks.get(i)).append(LINE_BREAK);
        }
        result.append(DIVIDER_LINE);

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

        result.append(DIVIDER_LINE).append(MATCHING_TASK);
        for (Task task : tasks) {
            if (task.description.contains(command)) {
                result.append(INDENT).append(count).append(".").append(task).append(LINE_BREAK);
                count++;
            }
        }
        result.append(DIVIDER_LINE);

        return result.toString();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A formatted message for marking a task as done.
     */
    public String showTaskMarkedAsDone(Task task) {
        return DIVIDER_LINE
                + MARK_DONE
                + INDENT + task + LINE_BREAK
                + DIVIDER_LINE;
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A formatted message for marking a task as not done.
     */
    public String showTaskMarkedAsNotDone(Task task) {
        return DIVIDER_LINE
                + MARK_NOT_DONE
                + INDENT + task + LINE_BREAK
                + DIVIDER_LINE;
    }

    /**
     * Displays a message indicating a new task has been added.
     *
     * @param size The total number of tasks after the addition.
     * @param task The task that has been added.
     * @return A formatted message for adding a new task.
     */
    public String showTaskAdded(int size, Task task) {
        return DIVIDER_LINE
                + ADD_TASK
                + INDENT + task + LINE_BREAK
                + HAVE + size + TASKS
                + DIVIDER_LINE;
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task  The task that has been deleted.
     * @param index The total number of tasks after the deletion.
     * @return A formatted message for deleting a task.
     */
    public String showTaskDelete(Task task, int index) {
        return DIVIDER_LINE
                + REMOVE_TASK
                + INDENT + task + LINE_BREAK
                + HAVE + index + TASKS
                + DIVIDER_LINE;
    }
}
