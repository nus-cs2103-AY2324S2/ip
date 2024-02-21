package bond.main;

import java.util.ListIterator;

import bond.task.Task;
import bond.task.TaskList;

/**
 * The Ui class is used to handle the user response message of the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class Ui {

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    public String newLine() {
        return System.lineSeparator();
    }

    /**
     * Shows the welcome message when the program starts.
     */
    public String showWelcome() {
        return "Hello! I'm Bond.\nWhat can I do for you?";
    }

    /**
     * Shows the taskList is empty message.
     */
    public String showTaskListEmpty() {
        return "There are no tasks in the list.";
    }

    /**
     * Shows the message when a task is added.
     *
     * @param newTask  The task that is added.
     * @param taskList The task list that the task is added to.
     */
    public String taskAdded(Task newTask, TaskList taskList) {
        return String.format(
                "Got it. I've added this task:\n      %s\nNow you have %d tasks in the list.",
                newTask.toString(), taskList.numberOfTasks());
    }


    /**
     * Shows the message when a task is updated.
     *
     * @param currentTaskDetails The details of the task before it is updated.
     * @param updatedTask        The task after it is updated.
     */
    public String taskUpdated(String currentTaskDetails, Task updatedTask) {
        return String.format(
                "Got it. The task:\n      %s\nhas been modified to\n      %s",
                currentTaskDetails, updatedTask.toString());
    }

    /**
     * Shows the message when a task is deleted.
     *
     * @param deletedTask The task that is deleted.
     * @param taskList    The task list that the task is deleted from.
     */
    public String taskDeleted(Task deletedTask, TaskList taskList) {
        return String.format(
                "Got it. I've removed this task:\n      %s\nNow you have %d tasks in the list.",
                deletedTask.toString(), taskList.numberOfTasks());
    }

    /**
     * Shows the message when a task is marked as done.
     *
     * @param markedTask The task that is marked as done.
     */
    public String taskMarked(Task markedTask) {
        return String.format(
                "Nice! I've marked this task as done:\n      %s",
                markedTask.toString());
    }

    /**
     * Shows the message when a task is marked as not done.
     *
     * @param unmarkedTask The task that is marked as not done.
     */
    public String taskUnmarked(Task unmarkedTask) {
        return String.format(
                "OK, I've marked this task as not done yet:\n      %s",
                unmarkedTask.toString());
    }


    private String appendTasks(TaskList taskList, String headerMsg) {
        StringBuilder builder = new StringBuilder();
        builder.append(headerMsg);

        ListIterator<Task> tasks = taskList.getTasks();

        while (tasks.hasNext()) {
            String foundTask = String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString());

            if (tasks.hasNext()) {
                foundTask += this.newLine();
            }

            builder.append(foundTask);
        }

        return builder.toString();
    }

    /**
     * Shows all tasks found.
     *
     * @param taskList The task list containing all tasks found.
     */
    public String showFoundTasks(TaskList taskList) {
        String header = "Here are the matching tasks in your list:\n";
        return appendTasks(taskList, header);
    }

    /**
     * Shows all tasks in the task list.
     *
     * @param taskList The task list to read from.
     */
    public String showList(TaskList taskList) {
        String header = "Here are the tasks in your list:\n";
        return appendTasks(taskList, header);
    }

    /**
     * Shows the goodbye message when the user exits the program.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
}
