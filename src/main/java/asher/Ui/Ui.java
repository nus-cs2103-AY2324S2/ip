package asher.Ui;

import java.util.ArrayList;

import asher.Tasks.TaskList;
import asher.Tasks.Task;

/**
 * Contains functions to handle the user interface of the bot.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() {}

    /**
     * Displays the welcome message at the start.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    /**
     * Displays the exit message at the end.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the add task message.
     * @param task The task that is added.
     */
    public void showAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
    }

    /**
     * Displays the remove task message.
     * @param removedTask The task that is deleted.
     */
    public void showRemoveTaskMessage(Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
    }

    /**
     * Displays the number of tasks in the list message.
     * @param totalTasks The total number of tasks.
     */
    public void showNumberOfTaskInListMessage(int totalTasks) {
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Display the task is marked message.
     * @param task The task that should be marked.
     */
    public void showMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" "  + task);
    }

    /**
     * Displays the task is unmarked message.
     * @param task The task that should be unmarked.
     */
    public void showUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays the task list message.
     * @param taskList The task list.
     */
    public void showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays the matching tasks message.
     * @param matchingTasks The tasks that have the common keyword.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks at all!");
        } else {
            System.out.println("Here are the matching tasks in your list:");

            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

}
