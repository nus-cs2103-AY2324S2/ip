package lia;

import java.util.ArrayList;

/**
 * The Ui class handles the interaction with the user by displaying messages and information.
 */
public class Ui {
    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j <= tasks.size(); j++) {
            Task task = tasks.get(j - 1);
            result.append(j).append(". ").append(task.toString()).append("\n");
        }

        return result.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkedAsDone(Task task) {
        return "I've marked this task as done:\n" + task.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showMarkedAsNotDone(Task task) {
        return "I've unmarked this task:\n" + task.toString();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task  The task that has been added.
     * @param tasks The updated list of tasks.
     */
    public String showAddedTask(Task task, TaskList tasks) {
        return "I've added this task:\n" + task.toString()
                + "\nYou have " + tasks.getSize() + " task(s) in the list.";
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task  The task that has been removed.
     * @param tasks The updated list of tasks.
     */
    public String showRemovedTask(Task task, TaskList tasks) {
        return "I've removed this task:\n" + task.toString()
                + "\nYou have " + tasks.getSize() + " task(s) in the list.";
    }

    /**
     * Displays a help message containing a list of valid commands and their usages.
     */
    public String showHelp() {
        return "list\n"
                + "- Lists out all your tasks\n"
                + "todo <description>\n"
                + "- Adds a task\n"
                + "deadline <description> /by <due by>\n"
                + "- Adds a task with a deadline\n(input date in yyyy-MM-dd format)\n"
                + "event <description> /from <starts at> to <ends at>\n"
                + "- Adds an event\n"
                + "mark <task number>\n"
                + "- Marks task at specified position as done\n"
                + "unmark <task number>\n"
                + "- Marks task at specified position as not done\n"
                + "delete <task number>\n"
                + " - Deletes task at specified position\n"
                + "exit\n"
                + "- Ends the conversation";
    }

    /**
     * Displays a message indicating that an invalid command has been entered.
     */
    public String showInvalidCommand() {
        return "Invalid command.\nType 'help' for a list of valid commands and their usages.";
    }
}
