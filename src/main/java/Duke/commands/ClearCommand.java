package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Represents a command to clear all tasks from the task list.
 * Extends the Command class.
 */
public class ClearCommand extends Command {

    /**
     * Constructs a ClearCommand object.
     * Calls the constructor of the superclass (Command).
     */
    public ClearCommand() {
        super();
    }

    /**
     * Executes the clear command.
     * Clears all tasks from the task list, updates the storage file, and returns a message indicating successful clearance.
     * @param tasks The TaskList object containing the tasks to be cleared.
     * @param ui The UI object for user interaction.
     * @param storage The Storage object for file operations.
     * @return A message indicating that the task list has been cleared.
     * @throws DukeException If there is an error in executing the clear command.
     */
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        // Clear the task list
        tasks.clearTaskList();
        // Get the current tasks after clearing
        ArrayList<Task> currentTask = tasks.getTasks();
        // Update the storage file with the current tasks
        storage.rewriteFile(currentTask);
        // Return a message indicating successful clearance
        return ui.listClearedMessage();
    }
}

