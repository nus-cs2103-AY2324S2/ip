package duke;

/**
 * The Command interface represents an executable command that can be performed
 * on a TaskList. Classes implementing this interface must provide an execute
 * method to carry out the specific command.
 */
public interface Command {
    /**
     * Executes the command, performing the specified action on the TaskList.
     *
     * @param tasks   The TaskList on which the command will be executed.
     * @param ui      The Ui object for displaying user interface messages.
     * @param storage The Storage object for saving and loading tasks to and from a file.
     * @throws DukeException If there is an issue executing the command.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

