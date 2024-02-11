package drake;
import java.util.ArrayList;

import drake.task.Task;
import drake.task.TaskList;

/**
 * Handles UI operations for the chatbot.
 */
public class Ui {

    /**
     * Returns a welcome message to the user.
     */
    public static String showWelcome() {
        return "____________________________________________________________\n" + " What's up everyone. I'm Drake.\n"
                + " How can I help?\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a goodbye message to the user.
     */
    public String showGoodbye() {
        return "____________________________________________________________\n"
                + " See you later, alligator! \n"
                + "____________________________________________________________";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to be returned.
     */
    public String showError(String message) {
        assert message != null && !message.isEmpty() : "Error message cannot be null or empty.";
        return "____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________";
    }

    /**
     * Returns the list of tasks as a string.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "TaskList object cannot be null.";
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("You asked for the tasks in your list? Here:\n");
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        sb.append("____________________________________________________________");
        return sb.toString();
    }

    /**
     * Returns a message confirming the addition of a task, the task itself, and the new total number of tasks.
     *
     * @param task The task that has been added.
     * @param size The new total number of tasks in the list.
     */
    public String showAddTask(Task task, int size) {
        assert task != null : "Task object cannot be null.";
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @param task The task that has been marked.
     */
    public String showMarkTask(Task task) {
        return "____________________________________________________________\n"
                + "Cool. I now declare this task marked as, done:\n"
                + task + "\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a message confirming that a task has been marked as not done.
     *
     * @param task The task that has been unmarked.
     */
    public String showUnmarkTask(Task task) {
        return "____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a message confirming the deletion of a task, the task itself, and the new total number of tasks.
     *
     * @param task The task that has been deleted.
     * @param size The new total number of tasks in the list.
     */
    public String showDeleteTask(Task task, int size) {
        return "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a message showing tasks that match the input.
     *
     * @param matchingTasks The tasks that match the input.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        sb.append("____________________________________________________________");
        return sb.toString();
    }
}