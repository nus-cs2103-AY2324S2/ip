package baron.managers;

import java.util.List;

import baron.models.Task;
/**
 * Manages all output by the bot
 */
public class UiManager {

    private static String formatOutput(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        return sb.toString();
    }
    /**
     * Informs user of added content but does not add anything itself
     *
     * @param task  Task added
     * @param count count of tasks currently in list after modification
     */
    public static String add(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it, I've added this task: \n  " + task);
        sb.append("\n");
        sb.append("Now you have " + count + " tasks in the list");
        return formatOutput(sb.toString());
    }

    /**
     * Informs user of deleted content
     *
     * @param task  Task added
     * @param count count of tasks currently in list after modification
     */
    public static String delete(Task task, int count) {
        String output = "Noted, I've removed this task: \n  " + task;
        output += "\nNow you have " + count + " tasks in the list";
        return formatOutput(output);
    }

    /**
     * Lists all tasks currently available in a pretty format
     */
    public static String list(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i));
            sb.append("\n");
        }
        return formatOutput(sb.toString());
    }

    /**
     * Prints output for when user finds tasks
     *
     * @param tasks tasks to print out
     */
    public static String find(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        sb.append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i));
            sb.append("\n");
        }
        return formatOutput(sb.toString());
    }

    /**
     * Marks the specified task
     *
     * @param task   Task to mark
     * @param isDone mark whether task is done or not
     */
    public static String mark(Task task, boolean isDone) {
        String doneStr = isDone ? "done" : "not done";
        String output = "Okay, I've marked this task as " + doneStr + ": \n" + task;
        return formatOutput(output);
    }
}
