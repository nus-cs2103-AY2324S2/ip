/*
 * Command.java
 * This class is the base class for all command-related classes in the Duke application.
 * It defines the common interface and properties that all command classes must have.
 */

package duke.command;

import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    /**
     * Executes the command based on user input.
     *
     * @param tasks   The TaskList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for saving and loading tasks.
     * @throws DukeException If an error related to Duke application occurs.
     * @throws IOException   If there is an input/output error while saving or loading tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, otherwise false.
     */
    public abstract boolean isExit();
}
