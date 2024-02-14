package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param index The index of the tasks to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done in the TaskList.
     * Shows a message indicating the task has been marked as done.
     * Saves the changes to the file.
     *
     * @param tasks   The TaskList that holds the list of tasks.
     * @param ui      The Ui to interact with the user.
     * @param storage The Storage to save the tasks to a file.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            tasks.mark(this.index);
            storage.save(tasks);
            return ui.showMarkMsg(tasks.getTasks().get(this.index - 1));
        } else {
            throw new DukeException("Invalid index. "
                    + "Please provide a valid index within the range 1 to "
                    + tasks.getTaskSize() + ".");
        }
    }

    /**
     * Checks if the MarkCommand is an exit command.
     *
     * @return false, as the MarkCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
