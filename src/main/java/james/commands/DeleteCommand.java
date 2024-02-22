package james.commands;

import java.io.IOException;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.tasks.Task;
import james.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Creates a DeleteCommand to delete the task at the specified index.
     *
     * @param indexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the delete command which results in removing the task from the task list,
     * displaying the deletion in the UI, and attempting to save the updated task list
     * to the storage.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui responsible for interactions with the user.
     * @param storage The Storage where the task list is persisted.
     * @throws DukeException If the index provided is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToRemove = tasks.deleteTask(indexToDelete); // Assuming indexToDelete is defined
            String successMessage = ui.showDeletedTask(taskToRemove, tasks.getSize());
            storage.save(tasks.getTasks());
            return successMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index provided is invalid.");
        } catch (IOException e) {
            String errorMessage = ui.showError("An error occurred while saving tasks: " + e.getMessage());
            e.printStackTrace();
            return errorMessage;
        }
    }

    /**
     * Indicates whether the application should terminate after the execution of
     * this command. For DeleteCommand, it always returns false.
     *
     * @return false as the application should not exit after deleting a task.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
