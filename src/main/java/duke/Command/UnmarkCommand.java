package duke.Command;

import duke.*;
import duke.Tasks.Task;
import duke.Tasks.TaskList;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the given task index.
     *
     * @param index The index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
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
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("OOPS!!! duke.Tasks.Task index is out of range.");
        }

        Task task = tasks.get(index - 1);
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

