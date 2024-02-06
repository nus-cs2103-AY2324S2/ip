package capone.ui.cli;

import capone.TaskList;
import capone.tasks.Task;
import capone.ui.Ui;

/**
 * The Cli class defines the interface for user interface operations via a
 * command line interface. It includes methods to print formatted messages
 * to the command line.
 *
 * @author Tay Rui-Jie
 */
public class Cli extends Ui {

    /**
     * Sends a goodbye message to the user.
     */
    @Override
    public String sendGoodbye() {
        String output = "Bye. Hope to see you again soon!\n";
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message after the user creates a Deadline task.
     *
     * @param taskList The list of tasks created.
     * @return The formatted output of the command.
     */
    @Override
    public String sendDeadline(TaskList taskList) {
        String output = String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d task(s) in the list.\n",
                taskList.getLastTask().toString(), taskList.getSize());
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message after the user deletes a task.
     *
     * @param taskList The list of tasks created.
     * @param deletedTask The task that was deleted.
     * @return The formatted output of the command.
     */
    @Override
    public String sendDelete(TaskList taskList, Task deletedTask) {
        String output = String.format("Noted. I've removed this task:\n%s\n"
                        + "Now you have %d tasks in the list.\n",
                deletedTask.toString(), taskList.getSize());
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message after the user creates an Event task.
     *
     * @param taskList The list of tasks created.
     * @return The formatted output of the command.
     */
    @Override
    public String sendEvent(TaskList taskList) {
        String output = String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d task(s) in the list.\n",
                taskList.getLastTask().toString(), taskList.getSize());
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message after the user searches for a task
     * has no results.
     *
     * @param keyword The list of tasks created.
     * @return The formatted output of the command.
     */
    @Override
    public String sendNoResults(String keyword) {
        String output = String.format("No results found for the given keyword %s\n", keyword);
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a message displaying the help information.
     * @return The formatted output of the command.
     */
    @Override
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
                + "8. find [keyword] - Finds all tasks that matches the given input keyword.\n";
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message showing the list of available tasks.
     *
     * @param taskList The list of tasks to display.
     * @return The formatted output of the command.
     */
    @Override
    public String sendList(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            String task = String.format("%d. %s\n", i + 1, taskList.getTask(i).toString());
            System.out.printf(task);
            output.append(task);
        }

        return output.toString();
    }

    /**
     * Sends a formatted message after the user marks a task.
     *
     * @param task The task to be marked as complete.
     * @return The formatted output of the command.
     */
    @Override
    public String sendMark(Task task) {
        String output = "Nice! I've marked this task as completed:\n" + task.toString() + "\n";
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message after the user creates a ToDo task.
     *
     * @param taskList The list of tasks created.
     * @return The formatted output of the command.
     */
    @Override
    public String sendTodo(TaskList taskList) {
        String output = String.format("Got it. I've added this task:\n%s\n"
                + "Now you have %d task(s) in the list.\n",
                taskList.getLastTask().toString(), taskList.getSize());
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a formatted message after the user unmarks a task.
     *
     * @param task The task to be marked as incomplete.
     * @return The formatted output of the command.
     */
    @Override
    public String sendUnmark(Task task) {
        String output = "Nice! I've marked this task as incomplete:\n" + task.toString() + "\n";
        System.out.printf(output);
        return output;
    }
}
