package duke.Command;

import duke.TaskList;
import duke.DukeException;
import duke.Task.Task;
import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {

    /**
     * Executes the delete command.
     * Removes the task at the specified index from the task list.
     * Throws a DukeException if the index is invalid.
     *
     * @param taskList the task list to delete the task from
     * @param command  the delete command
     * @return a string indicating the success of the delete operation
     * @throws DukeException if the index is invalid
     */
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task removedTask = taskList.removeTask(index);
        return "Noted. I've removed this task:\n" + removedTask + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
    }
}
