package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The `EditCommand` class represents a command that edit task's status in the task list.
 * It provides methods to edit task's status in the task list, and to decide whether the program should continue.
 * It extends the `Command` class.
 */
public class EditCommand extends Command {

    private final boolean isMark;
    private final int index;

    /**
     * Creates a command that edit task's status in task list when executed.
     *
     * @param index The index of task to be deleted in the task list.
     */
    public EditCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param tasks Existing tasks.
     * @param ui The Ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException For any error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Index out of bound handler
        if (index >= tasks.getItems().size()) {
            throw new DukeException("The index of task cannot be larger than number of task.");
        } else if (index < 0) {
            throw new DukeException("The index of task must be positive integer.");
        }

        if (this.isMark) {
            tasks.markDone(index);
            ui.mark(tasks.getItem(index));
        } else {
            tasks.unmarkDone(index);
            ui.mark(tasks.getItem(index));
        }

        storage.saveChanges(tasks);
    }

    /**
     * Returns False.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
