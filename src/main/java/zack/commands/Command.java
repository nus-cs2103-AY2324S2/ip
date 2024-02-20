package zack.commands;

import java.io.IOException;

import zack.ZackException;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructs a Command object with the initial state of not being an exit command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command with the given TaskList, Ui, and Storage objects.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI for displaying messages to the user.
     * @param storage The Storage for saving and loading tasks.
     * @throws ZackException If there is an error while executing the command.
     * @throws IOException  If there is an error with input/output operations.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException;

}
