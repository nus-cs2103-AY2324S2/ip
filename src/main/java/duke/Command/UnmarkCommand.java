package duke.Command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.Tasks.Task;
import duke.Tasks.TaskList;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int INDEX;

    /**
     * Constructs an UnmarkCommand object with the given task index.
     *
     * @param TASK_INDEX The index of the task to mark as not done.
     */
    public UnmarkCommand(int TASK_INDEX) {
        this.INDEX = TASK_INDEX;
    }


    /**
     * Executes the UnmarkCommand, marking the specified task as not done.
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

        Task task = tasks.get(INDEX - 1);
        task.markNotDone();
        ui.showUnmarkedMessage(task);
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

