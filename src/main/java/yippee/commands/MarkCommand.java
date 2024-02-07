package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

/**
 * Represents commands to mark tasks as complete or incomplete.
 */
public class MarkCommand extends Command {
    private boolean isUnmarkCommand;
    private boolean isExit = false;
    private int number;

    /**
     * Constructor to commands of type MarkCommand.
     * @param isUnmark Boolean to indicate if the task is to mark as incomplete.
     * @param number Index of task to be marked.
     */
    public MarkCommand(boolean isUnmark, int number) {
        super(false);
        this.isUnmarkCommand = isUnmark;
        this.number = number;
    }

    /**
     * {@inheritDoc}
     * @param tasks TaskList of active tasks
     * @param ui Ui instance to print responses.
     * @param storage Storage instance to store any data.
     * @throws InvalidCommandException If command is of invalid format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (isUnmarkCommand) {
            tasks.unmarkTask(number);
        } else {
            tasks.markTask(number);
        }
    }
}
