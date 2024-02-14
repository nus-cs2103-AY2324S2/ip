package duke.Command;

import duke.TaskList;
import duke.DukeException;
import duke.Task.Task;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {

    /**
     * Executes the unmark command.
     *
     * @param taskList the task list containing the tasks
     * @param command  the command string
     * @return the result message after executing the command
     * @throws DukeException if the task number is invalid
     */
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        return "Nice! I've marked this task as not done:\n" + task;
    }
}