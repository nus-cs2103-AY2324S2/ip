package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command in the Duke application. All specific command
 * classes should extend this base class and implement the execute() method.
 * <p>
 * Commands are responsible for performing actions in response to user input.
 * They interact with the task list, user interface (UI), and storage components
 * to carry out their respective tasks. Each command may have a specific action
 * associated with it, such as adding a task, listing tasks, marking tasks as done,
 * and more.
 */
public abstract class Command {

    /**
     * Constructs a new Command object.
     * This constructor is empty and serves as a placeholder.
     */
    public Command() {}

    /**
     * Indicates whether executing this command should result in exiting the Duke application.
     * By default, commands do not trigger an exit.
     *
     * @return true if executing this command should exit the application, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command's action, which may involve interacting with the task list,
     * user interface (UI), and storage. Specific command classes must implement this
     * method to define their behavior when executed.
     *
     * @param tasks   The task list containing tasks managed by the application.
     * @param ui      The user interface component for displaying messages to the user.
     * @param storage The storage component responsible for saving and loading tasks.
     * @throws DukeException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
