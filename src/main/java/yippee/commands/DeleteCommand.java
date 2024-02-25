package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

/**
 * Represents commands to delete a task.
 */
public class DeleteCommand extends Command {
    private int deleteNumber;
    private static int deleteCount = 0;
    public DeleteCommand(int number) {
        super(false);
        this.deleteNumber = number;
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
        deleteCount++;
        return tasks.deleteTask(this.deleteNumber);
    }

    public static int getDeleteCount() {
        return deleteCount;
    }

}
