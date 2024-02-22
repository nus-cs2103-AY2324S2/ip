package james.commands;

import java.io.IOException;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.tasks.Task;
import james.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int indexToMark;

    /**
     * Creates a new MarkCommand with the given index of the task to mark.
     *
     * @param indexToMark Index of the task to mark.
     */
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     *
     * @param tasks   Task list to mark the task in.
     * @param ui      Ui to interact with the user.
     * @param storage Storage to save the task list to.
     * @throws DukeException If an error occurs while marking the task as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String result;
        try {
            Task taskToMark = tasks.getTask(indexToMark);
            taskToMark.markAsDone();
            result = ui.showMarkedTask(taskToMark); // Assumes this method returns a String message
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index provided is invalid.");
        } catch (IOException e) {
            result = ui.showError("An error occurred while saving tasks: " + e.getMessage());
        }
        return result;
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
