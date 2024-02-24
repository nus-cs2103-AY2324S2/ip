package james.commands;

import java.io.IOException;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.tasks.Task;
import james.ui.Ui;

/**
 * Represents a command to sort the task list.
 */
public class SortCommand extends Command{
    /**
     * Executes the command to sort the task list.
     *
     * @param tasks   Task list to sort.
     * @param ui      Ui to interact with the user.
     * @param storage Storage to save the task list to.
     * @throws DukeException If an error occurs while sorting the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.sortTasks();
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            String errorMessage = ui.showError("An error occurred while saving tasks: " + e.getMessage());
            e.printStackTrace();
            return errorMessage;
        }
        return ui.showTaskList(tasks);    
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
