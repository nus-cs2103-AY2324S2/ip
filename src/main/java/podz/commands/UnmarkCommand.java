package podz.commands;

import podz.exceptions.PodzException;
import podz.ui.Ui;

/**
 * Represents a command to unmark a task in the task manager.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the specified task index.
     *
     * @param index the index of the task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command to unmark a task.
     *
     * @param ui the user interface that interacts with the user
     * @throws PodzException if the task index is invalid
     */
    @Override
    public void execute(Ui ui) throws PodzException {
        if (index < 0 || index >= super.taskList.getSize()) {
            throw new PodzException("Oh no!!! Invalid task index!");
        }
        super.taskList.unmarkTask(index);
        ui.printUnmarked(super.taskList.getTask(index));
    }
}
