package podz.commands;

import podz.exceptions.PodzException;
import podz.ui.Ui;

/**
 * Represents a command to mark a task as completed in the task manager.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the specified task index.
     *
     * @param index the index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command to mark a task as completed.
     *
     * @param ui the user interface that interacts with the user
     * @throws PodzException if the task index is invalid
     */
    @Override
    public void execute(Ui ui) throws PodzException {
        if (index < 0 || index >= super.taskList.getSize()) {
            throw new PodzException("Oh no!!! Invalid task index!");
        }
        super.taskList.markTask(index);
        ui.printMarked(super.taskList.getTask(index));
    }
}