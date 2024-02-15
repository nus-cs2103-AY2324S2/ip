package tommy.command;

import tommy.Ui;
import tommy.Storage;

import tommy.task.TaskList;

import tommy.exception.InvalidArgumentException;

/**
 * Abstract class that is the parent class of all commands.
 */
public abstract class Command {

    /**
     * Returns the availability of the program to take in commands afterwards,
     * which is true for all commands except BYE.
     *
     * @returns Availability to take in input commands.
     */
    public boolean isActive() {
        return true;
    }

    /**
     * Executes the command and updates the taskList and storage if necessary, then
     * print out relevant information about the command.
     *
     * @param storage Database to save the updated task list into.
     * @param taskList Task list to alter and update if necessary.
     * @param ui User interface to call its methods to print the relevant information.
     * @returns Response of Tommy chatbot as a String.
     * @throws InvalidArgumentException If input argument does not satisfy the specified task's format.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException;
}
