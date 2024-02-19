package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskCommandPair;
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

    /**
     * {@inheritDoc}
     *
     * Marks the task at the given index as not done and saves the updated task list.
     * Displays the task that was unmarked to the user.
     *
     * @throws DukeException If the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() <= index) {
            throw new DukeException("There is nothing to be unmarked");
        } else {
            Task task = tasks.get(index);
            task.unmarkDone();
            Duke.push(new TaskCommandPair(task, this));
            storage.save(tasks);
            return ui.showUnmarked(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
