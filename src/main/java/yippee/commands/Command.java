package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;

/**
 * Represents commands parsed from user input.
 */
public abstract class Command {
    private boolean isExit;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes command.
     * @param tasks Tasklist of all current tasks.
     * @param ui Ui element to generate replies for user.
     * @param storage Storage to store and load data after each action.
     * @return String output to be displayed to user.
     * @throws InvalidCommandException If command format is inaccurate.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;
}
