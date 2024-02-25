package command;

import roland.RolandException;
import roland.Storage;
import roland.TaskList;
import roland.Ui;

/**
 * The ClearCommand class represents a command to clear all tasks from the task list.
 * This command is executed by calling the execute method, which clears the task list and updates the storage file.
 */
public class ClearCommand extends Command {

    /**
     * Executes the ClearCommand by clearing all tasks from the task list and updating the storage file.
     *
     * @param tasks   The task list to be cleared.
     * @param ui      The user interface to display messages.
     * @param storage The storage object to update the storage file.
     * @return A message indicating that all tasks have been cleared.
     * @throws RolandException If the task list is already empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RolandException {
        if (tasks.size() == 0) {
            throw new RolandException("Your task list is empty. Add one now!");
        }
        tasks.clear();
        super.serializeArrayList(tasks.getList(), storage.getFilePath());
        return "All your tasks have been cleared!";
    }
}
