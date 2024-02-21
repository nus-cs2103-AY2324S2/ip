package duke.ui;

import duke.task.Task;

import java.util.Scanner;

/**
 * Contains functions that handle the user interface of cookie.
 */
public class Ui {

    /**
     * Displays the welcome message when the application starts.
     *
     * @return The welcome message.
     */
    public String showWelcomeMessage() {
        return "Heyy! I'm Cookie! :) \n"+ "What can I do for you?";
    }

    /**
     * Displays the goodbye message when the application is terminated by the user.
     *
     * @return The goodbye message.
     */
    public String showByeMessage() {
        return "Byee! Hope to see you again soon :)";
    }

    /**
     * Displays a message upon the addition of a new task.
     *
     * @param t       The task that was added.
     * @param counter The current count of tasks.
     * @return The message confirming the addition of the task.
     */
    public String showAddTaskMessage(Task t, int counter) {
        return "Okay! I've added this task: \n" + t.toString() + "\n" + "Now you have " + (counter + 1) + " tasks in the list.";
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param t       The task that was removed.
     * @param counter The current count of tasks.
     * @return The message confirming the removal of the task.
     */
    public String showRemoveTaskMessage(Task t, int counter) {
        return "Okay! I've removed this task: \n" + t.toString() + "\n" + "Now you have " + (counter - 1) + " tasks in the list.";
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param t The task that has been marked as done.
     * @return The message confirming the task's completion.
     */
    public String showMarkTaskDoneMessage(Task t) {
        return "Nice! I've marked this task as done: \n" + t.toString();
    }


    /**
     * Displays the current task list.
     *
     * @param taskArr The array containing the tasks.
     * @param counter The current count of tasks.
     * @return The formatted string representing the task list.
     */
    public String showTaskList(Task[] taskArr, int counter) {
        String toPrint = "Here are your tasks: \n";

        for (int i = 1; i <= counter; i++) {
            Task task = taskArr[i - 1];
            String taskDesc = task.toString();
            toPrint = toPrint + i + ". " + taskDesc + '\n';
        }

        return toPrint;
    }

    /**
     * Displays tasks containing the keyword.
     *
     * @param tasks The array containing the tasks.
     * @return The formatted string representing the tasks containing the keyword.
     */
    public String showTasksContainingKeyword(Task[] tasks, String keyword) {
        String toPrint = "Here are the tasks containing " + keyword + ": \n";
        int counter = 0;
        Task t = tasks[counter];
        while(t != null) {
            String taskDesc = t.toString();
            toPrint += (counter + 1) + ". " + taskDesc + "\n";
            counter++;
            t = tasks[counter];
        }
        return toPrint;
    }

    /**
     * Displays tasks containing the keyword.
     *
     * @param tasks The array containing the tasks.
     * @return The formatted string representing the tasks containing the keyword.
     */
    public String displayTaggedTasks(Task[] tasks, String tag) {
        String toPrint = "Here are the tasks tagged " + tag + ": \n";
        int counter = 0;
        Task t = tasks[counter];
        while(t != null) {
            String taskDesc = t.toString();
            toPrint += (counter + 1) + ". " + taskDesc + "\n";
            counter++;
            t = tasks[counter];
        }
        return toPrint;
    }
}
