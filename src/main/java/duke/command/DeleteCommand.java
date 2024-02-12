package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The `DeleteCommand` class represents a command that deletes task from the task list.
 * It provides methods to deletes task from the task list, and to decide whether the program should continue.
 * It extends the `Command` class.
 */
public class DeleteCommand extends Command{

    private final int index;

    /**
     * Creates a command that deletes task in task list when executed.
     *
     * @param index The index of task to be deleted in the task list.
     */
    public DeleteCommand(int index) {
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

        ui.delete(tasks.getItem(index), tasks);
        tasks.delete(index);
        storage.saveChanges(tasks);
    }

    /**
     * Returns False to indicate don't exit program after this command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }


}
