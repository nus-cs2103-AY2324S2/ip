package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskCommandPair;
import duke.task.TaskList;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to delete.
     */
    private final int index;

    /**
     * Constructs a new {@code DeleteCommand} with the given index.
     *
     * Deletes the task at the given index from the task list.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the task at the given index from the task list and saves the updated task list.
     * Displays the task that was deleted to the user.
     *
     * @throws DukeException If the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() <= index) {
            throw new DukeException("There is nothing to be deleted");
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            Duke.push(new TaskCommandPair(task, this));
            storage.save(tasks);
            return ui.showDeleted(task) + "\n" + ui.showSize(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
