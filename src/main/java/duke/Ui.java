package duke;

import java.util.ArrayList;
import duke.task.Task;

/**
 * The Ui class is responsible for all user interface operations in the Duke application.
 * It handles displaying messages to the user, including welcome and exit messages,
 * error messages, and information about tasks.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a loading error message when the application cannot load the task list from a file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    /**
     * Displays an exit message to the user when the application is closing.
     */
    public void showExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        if (taskArrayList.isEmpty()) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskArrayList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Displays the number of tasks in the task list.
     *
     * @param tasks The TaskList whose size is to be displayed.
     */
    public void showNumTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTasksSize() + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Displays a message indicating a task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done.
     * @return The message to be displayed.
     */
    public String showMarkAsUndoneMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param removedTask The task that has been removed.
     * @param tasks The updated TaskList after the task has been removed.
     */
    public void showDeleteMessage(Task removedTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + removedTask.toString());
        showNumTasks(tasks);
    }

    /**
     * Displays a message indicating a new task has been added.
     *
     * @param newTask The new task that has been added.
     * @param tasks The updated TaskList after the new task has been added.
     */
    public void showAddTaskMessage(Task newTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n " + newTask.toString());
        showNumTasks(tasks);
    }
}
