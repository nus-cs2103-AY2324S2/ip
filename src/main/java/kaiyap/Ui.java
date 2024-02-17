package kaiyap;

import java.util.List;

/**
 * The Ui class handles the user interface for the KaiYap application.
 * It is responsible for displaying messages, errors, and tasks to the user.
 * This class works in conjunction with the TaskList class to present task-related information.
 */
public class Ui {

    private static final String SEPARATOR = "\t_______________________________________\n";
    private TaskList taskList;

    /**
     * Sets the TaskList object for the UI to interact with.
     *
     * @param taskList The TaskList to be set for UI operations.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints a greeting message to the user.
     * This method is typically called at the start of the application.
     */
    public String sayHello() {
        return (SEPARATOR
                + "\tHello! I'm KaiYap.\n"
                + "\tWhat can I do for you?\n"
                + SEPARATOR
            );
    }

    /**
     * Prints a farewell message to the user.
     * This method is typically called at the end of the application.
     */
    public String sayBye() {
        return (SEPARATOR
                + "\tBye. Hope to see you again soon!\n"
                + SEPARATOR);
    }

    /**
     * Lists all the tasks currently in the task list.
     * This method prints each task with its index in a formatted manner.
     */
    public String listInputs() {
        StringBuilder output = new StringBuilder();
        output.append(SEPARATOR).append("\tHere are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            output.append("\t").append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        output.append(SEPARATOR);
        return output.toString();
    }

    /**
     * Prints an error message.
     *
     * @param error The error message to be printed.
     */
    public String printError(String error) {
        StringBuilder output = new StringBuilder(SEPARATOR);
        String[] errorWords = error.split(" ");
        int count = 0;
        for (String word : errorWords) {
            if (count + word.length() > 40) {
                count = word.length();
                output.append("\n\t").append(word).append(" ");
            } else {
                output.append(word).append(" ");
                count += word.length();
            }
        }
        output.append("\n" + SEPARATOR);
        return output.toString();
    }

    /**
     * Prints a list of tasks found.
     *
     * @param tasks the list of tasks that matched the search phrase.
     */
    public String printTasksFound(List<Task> tasks) {
        StringBuilder output = new StringBuilder((
                SEPARATOR
                        + "\tHere are the matching tasks in your list:\n"
        ));
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\t").append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        output.append(SEPARATOR);
        return output.toString();
    }

    /**
     * Prints the output after deleting a task.
     *
     * @param t the task to be deleted
     * @param size the size of the list after deletion
     */
    public String printTaskRemoved(Task t, int size) {
        return (
                SEPARATOR
                        + "\tNoted. I've removed this task:\n"
                        + "\t\t" + t.toString()
                        + "\n\tYou now have " + size + (size == 1 ? " task" : " tasks") + " in the list.\n"
                        + SEPARATOR
            );
    }

    /**
     * Formats and returns a string listing tasks marked as done.
     *
     * @param values Indices of tasks to be marked as done.
     * @return Formatted string of tasks marked as done.
     */
    public String printTaskMarked(int... values) {
        StringBuilder output = new StringBuilder(SEPARATOR);
        output.append("\tNice! I've marked these tasks as done:\n");
        for (int i : values) {
            output.append("\t\t").append(taskList.get(i - 1).toString()).append("\n");
        }
        output.append(SEPARATOR);
        return output.toString();
    }

    /**
     * Returns a message stating a task is not done, identified by its index.
     *
     * @param numericIndex Index of the task in task list.
     * @return Formatted message indicating the task is not marked as done.
     */
    public String printTaskUnmarked(int numericIndex) {
        return (
                SEPARATOR
                        + "\tOK, I've marked this task as not done yet:\n"
                        + "\t\t" + taskList.get(numericIndex).toString() + "\n"
                        + SEPARATOR
            );
    }

    /**
     * Displays the information about a newly added task.
     * It prints a message confirming that the task has been added to the list
     * and shows the total number of tasks currently in the list.
     *
     * @param task The task that has been added to the task list.
     */
    public String echo(Task task) {
        return (SEPARATOR
                + "\tGot it. I've added this task:\n\t\t" + task.toString()
                + "\n\tYou now have " + this.taskList.size() + (this.taskList.size() == 1 ? " task" : " tasks")
                + " in the list.\n"
                + SEPARATOR);
    }
}
