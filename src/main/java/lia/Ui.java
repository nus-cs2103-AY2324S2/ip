package lia;

import java.util.ArrayList;

/**
 * The Ui class handles the interaction with the user by displaying messages and information.
 */
public class Ui {
    /**
     * Displays an error message when there is an issue loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Hello, I'm Lia! To learn how to use me, type 'help'.");
    }

    /**
     * Displays a welcome message when Lia is started.
     */
    public void showWelcomeMessage() {
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTasks(ArrayList<Task> tasks) {
        for (int j = 1; j <= tasks.size(); j++) {
            Task task = tasks.get(j - 1);
            System.out.println(j + ". " + task.toString());
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedAsDone(Task task) {
        System.out.println("I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showMarkedAsNotDone(Task task) {
        System.out.println("I've marked this task as not done:");
        System.out.println(task.toString());
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task  The task that has been added.
     * @param tasks The updated list of tasks.
     */
    public void showAddedTask(Task task, TaskList tasks) {
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.println("You have " + tasks.getSize() + " task(s) in the list.");
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task  The task that has been removed.
     * @param tasks The updated list of tasks.
     */
    public void showRemovedTask(Task task, TaskList tasks) {
        System.out.println("I've removed this task:");
        System.out.println(task.toString());
        System.out.println("You have " + tasks.getSize() + " task(s) in the list.");
    }

    /**
     * Displays a help message containing a list of valid commands and their usages.
     */
    public void showHelp() {
        System.out.println("list\n" +
                "- Lists out all your tasks\n" +
                "todo <task description>\n" +
                "- Adds a task\n" +
                "deadline <task description> /by <due by>\n" +
                "- Adds a task with a deadline (input date in yyyy-MM-dd format)\n" +
                "event <event description> /from <starts at> to <ends at>\n" +
                "- Adds an event\n" +
                "mark <task number>\n" +
                "- Marks task at specified position as done\n" +
                "unmark <task number>\n" +
                "- Marks task at specified position as not done\n" +
                "delete <task number>\n" +
                " - Deletes task at specified position\n" +
                "exit\n" +
                "- Ends the conversation");
    }

    /**
     * Displays a message indicating that an invalid command has been entered.
     */
    public void showInvalidCommand() {
        System.out.println("Invalid command. Type help for a list of valid commands and their usages.");
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Goodbye!");
    }
}