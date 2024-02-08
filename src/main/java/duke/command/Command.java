package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * An {@code Command} represents an operation that modifies the state of the application.
 */
public abstract class Command {

    /**
     * Executes the command, modifying the state of the application.
     *
     * @param tasks The {@code TaskList} to modify.
     * @param ui The {@code Ui} to display the results of the command.
     * @param storage The {@code Storage} to save the modified state.
     * @throws DukeException If the command cannot be executed due to an error.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

<<<<<<< HEAD
    /**
     * Returns whether the command should exit the application.
     *
     * @return {@code true} if the command should exit the application, otherwise {@code false}.
     */
=======
>>>>>>> branch-A-CodingStandard
    public abstract boolean isExit();
}
