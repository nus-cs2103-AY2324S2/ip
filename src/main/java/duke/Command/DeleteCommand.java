package duke.Command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.Tasks.Task;
import duke.Tasks.TaskList;


/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int INDEX;

    /**
     * Constructs a DeleteCommand object with the given task index.
     *
     * @param TASK_INDEX The index of the task to be deleted.
     */
    public DeleteCommand(int TASK_INDEX) {
        this.INDEX = TASK_INDEX;
    }


    /**
     * Executes the DeleteCommand, deleting a task from the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 1 || INDEX > tasks.size()) {
            throw new DukeException("OOPS!!! duke.Tasks.Task index is out of range.");
        }

        Task deletedTask = tasks.get(INDEX - 1);
        int count = tasks.size() - 1;
        tasks.delete(INDEX - 1);
        Ui.showDeletedMessage(deletedTask, count);
        storage.save(tasks.getAllTasks());

    }

    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns false, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

