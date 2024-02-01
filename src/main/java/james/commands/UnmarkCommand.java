package james.commands;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasks.Task;
import james.tasklist.TaskList;
import james.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    /**
     * Constructor for an unmark command.
     * 
     * @param indexToUnmark
     */
    public UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }
    
    /**
     * Executes the command to unmark a task as not done in the task list.
     *
     * @param tasks   Task list to unmark the task in.
     * @param ui      Ui to interact with the user.
     * @param storage Storage to save the task list to.
     * @throws DukeException If an error occurs while unmarking the task as not done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToUnmark = tasks.getTask(indexToUnmark);
            taskToUnmark.markAsNotDone();
            ui.showUnmarkedTask(taskToUnmark);
            storage.save(tasks.getTasks()); // Handle exceptions appropriately
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index provided is invalid.");
        } catch (IOException e) {
            ui.showError("An error occurred while saving tasks: " + e.getMessage());
        }
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
