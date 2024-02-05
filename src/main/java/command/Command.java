package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;

/**
 * The abstract Command class serves as a blueprint for various commands that can be executed
 * in the task management application. It provides an execute method to be implemented by its
 * concrete subclasses for carrying out specific actions, and a default isExit method to check
 * if the program should quit.
 *
 * Subclasses of Command are expected to provide concrete implementations for the execute method,
 * defining the behavior associated with the specific command.
 *
 * @author wolffe88
 */

public abstract class Command {



    /**
     * @param tasks The taskList that stores the task
     * @param ui The user interface that outputs to the terminal
     * @param storage The storage path to store persistent data
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the program should exit.
     *
     * @return A boolean value indicating whether the program should exit (true) or continue running (false).
     */
    public boolean isExit() {
        return false;
    }
}
