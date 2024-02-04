package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import duke.exception.InvalidArgumentException;

/**
 * Abstract class that is the parent class of all commands.
 */
public abstract class Command {

    protected static boolean isActive = true;

    /**
     * Returns the value of the isActive boolean, which represents the
     * availability of the program to take in commands.
     *
     * @returns Availability to take in input commands.
     */
    public boolean getActive() {
        return this.isActive;
    }

    /**
     * Executes the command and updates the taskList and storage if necessary, then
     * print out relevant information about the command.
     *
     * @param storage Database to save the updated task list into.
     * @param taskList Task list to alter and update if necessary.
     * @param ui User interface to call its methods to print the relevant information.
     */
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException;
}
