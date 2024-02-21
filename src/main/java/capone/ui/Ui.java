package capone.ui;

import capone.TaskList;
import capone.tasks.Task;

/**
 * The Cli class defines the interface for user interface operations via a
 * command line interface. It includes methods to print formatted messages
 * to the command line.
 *
 * @author Tay Rui-Jie
 */
public class Ui {
    /**
     * Prints a welcome message with the Capone logo.
     *
     * @return Return the Capone logo.
     */
    public String printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        String output = String.format("Woof! I'm\n\n%s\n\nYour very own personal task manager.\n"
                + "What can I do for you today?\n%n", logo);
        return output;
    }

    /**
     * Sends a goodbye message to the user.
     */
    public String sendGoodbye() {
        String output = "Bye. Hope to see you again soon!\n";
        return output;
    }

    /**
     * Sends a formatted message after the user creates a Deadline task.
     *
     * @param taskList The list of tasks created.
     * @return The formatted output of the command.
     */
    public String sendDeadline(TaskList taskList) {
        String output = String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d task(s) in the list.\n",
                taskList.getLastTask().toString(), taskList.getSize());
        return output;
    }

    /**
     * Sends a formatted message after the user deletes a task.
     *
     * @param taskList The list of tasks created.
     * @param deletedTask The task that was deleted.
     * @return The formatted output of the command.
     */
    public String sendDelete(TaskList taskList, Task deletedTask) {
        String output = String.format("Noted. I've removed this task:\n%s\n"
                        + "Now you have %d tasks in the list.\n",
                deletedTask.toString(), taskList.getSize());
        return output;
    }

    /**
     * Sends a formatted message after the user creates an Event task.
     *
     * @param taskList The list of tasks created.
     * @return The formatted output of the command.
     */
    public String sendEvent(TaskList taskList) {
        String output = String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d task(s) in the list.\n",
                taskList.getLastTask().toString(), taskList.getSize());
        return output;
    }

    /**
     * Sends a formatted message after the user searches for a task
     * has no results.
     *
     * @param keyword The list of tasks created.
     * @return The formatted output of the command.
     */
    public String sendNoResults(String keyword) {
        String output = String.format("No results found for the given keyword %s\n", keyword);
        return output;
    }

    /**
     * Sends a message displaying the help information.
     * @return The formatted output of the command.
     */
    public String sendHelp() {
        String output = "Commands I understand:\n"
                + "1. list - Lists the tasks entered.\n"
                + "2. todo [description] - Creates a new capone.tasks.ToDo task. Remember to enter the description!\n"
                + "3. deadline [description] /by [date] - Creates a new capone.tasks.Deadline task.\n"
                + "   Remember to enter the description and date!\n"
                + "   Dates are recognised in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n"
                + "4. event [description] /from [date] /to [date] - Creates a new capone.tasks.Event task.\n"
                + "   Remember to enter the description, as well as the start and end date!\n"
                + "   Dates are recognised in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n"
                + "5. mark [index] - Marks a task as completed. Use this in conjunction with the 'list' command!\n"
                + "6. unmark [index] - Unmarks a task. Use this in conjunction with the 'list' command!\n"
                + "7. delete [index] - Deletes a task. Use this in conjunction with the 'list' command!\n"
                + "8. find [keyword] - Finds all tasks that matches the given input keyword.\n"
                + "9. update [index] [new description] - Updates a task's description. Use this in"
                + " conjunction with the 'list' command!.\n";
        return output;
    }

    /**
     * Sends a formatted message showing the list of available tasks.
     *
     * @param taskList The list of tasks to display.
     * @return The formatted output of the command.
     */
    public String sendList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "The current task list is empty.\n";
        }

        return this.formatList(taskList);
    }

    /**
     * Formats a task list with numbers.
     *
     * @param taskList The list of tasks to be formatted.
     * @return The formatted output of the task list.
     */
    private String formatList(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            String task = String.format("%d. %s\n", i + 1, taskList.getTask(i).toString());
            output.append(task);
        }

        assert !output.toString().isEmpty() : "Formatted task list output should not be empty";

        return output.toString();
    }

    /**
     * Sends a formatted message after the user marks a task.
     *
     * @param task The task to be marked as complete.
     * @return The formatted output of the command.
     */
    public String sendMark(Task task) {
        String output = "Nice! I've marked this task as completed:\n" + task.toString() + "\n";
        return output;
    }

    /**
     * Sends a formatted message after the user creates a ToDo task.
     *
     * @param taskList The list of tasks created.
     * @return The formatted output of the command.
     */
    public String sendTodo(TaskList taskList) {
        String output = String.format("Got it. I've added this task:\n%s\n"
                + "Now you have %d task(s) in the list.\n",
                taskList.getLastTask().toString(), taskList.getSize());
        return output;
    }

    /**
     * Sends a formatted message after the user unmarks a task.
     *
     * @param task The task to be marked as incomplete.
     * @return The formatted output of the command.
     */
    public String sendUnmark(Task task) {
        String output = "Nice! I've marked this task as incomplete:\n" + task.toString() + "\n";
        return output;
    }

    /**
     * Sends a formatted message after the user updates a task description.
     *
     * @param task The task to be updated.
     * @return The formatted output of the command.
     */
    public String sendUpdate(Task task) {
        String output = "Nice! I've updated the task description to '" + task.getDescription() + "'\n"
                + "The task looks like this now:\n" + task.toString() + "\n";
        return output;
    }
}
