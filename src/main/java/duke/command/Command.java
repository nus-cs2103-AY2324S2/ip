package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The `Command` class represents a general command for the Duke program.
 * It provides methods that can execute the given command, and to decide whether the program should continue.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks Existing tasks.
     * @param ui The Ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException For any error.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns True if and only if the command is `ExitCommand`.
     *
     * @return True if and only if the command is `ExitCommand`.
     */
    public abstract boolean isExit();
}
