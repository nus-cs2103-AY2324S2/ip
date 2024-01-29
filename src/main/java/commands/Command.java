package commands;

import exception.TodoFormatException;
import exception.DeadlineFormatException;
import exception.UncleBobException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents the commands that Uncle Bob can execute.
 */
abstract public class Command {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    public Command() {};

    /**
     * Executes command.
     * To be overwritten by child classes.
     *
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @param ui      The Ui object responsible for user interface interactions.
     * @throws UncleBobException If user input is not in the correct format.
     */
    abstract public void execute(TaskList tasks, Storage storage, Ui ui) throws UncleBobException;

    public static boolean isExit(Command c) {
        return c instanceof ExitCommand; // instanceof returns false if it is null
    }
}
