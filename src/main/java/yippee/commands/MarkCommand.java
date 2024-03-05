package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

/**
 * Represents commands to mark tasks as complete or incomplete.
 */
public class MarkCommand extends Command {
    private static int markCompleteCount = 0;
    private static int markInCompleteCount = 0;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        String response = "";
        if (isUnmarkCommand) {
            markInCompleteCount++;
            response = tasks.unmarkTask(number);
        } else {
            markCompleteCount++;
            response = tasks.markTask(number);
        }
        storage.resetSave();
        storage.storeData(tasks);
        return response;
    }

    public static int getMarkCompleteCount() {
        return markCompleteCount;
    }

    public static int getMarkInCompleteCount() {
        return markInCompleteCount;
    }
}
