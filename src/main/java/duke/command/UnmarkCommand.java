package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {

    /**
     * The index of the task to mark as not done.
     */
    private final int index;

    /**
     * Constructs a new {@code UnmarkCommand} with the given index.
     *
     * Marks the task at the given index as not done.
     *
     * @param index The index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

<<<<<<< HEAD
    /**
     * {@inheritDoc}
     *
     * Marks the task at the given index as not done and saves the updated task list.
     * Displays the task that was unmarked to the user.
     *
     * @throws DukeException If the index is out of bounds.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() <= index) {
            throw new DukeException("There is nothing to be unmarked");
        } else {
            Task task = tasks.get(index);
            task.unmarkDone();
            storage.save(tasks);
            ui.showUnmarked(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
