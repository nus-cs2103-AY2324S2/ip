package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;

/**
 * The Command class is an abstract class that serves as a base for the various command types.
 * It provides a method for executing the command and a method for checking if the command is done.
 */
public abstract class Command {
    protected boolean isDone;

    /**
     * Constructs a Command instance with an initial status.
     *
     * @param isDone Flag indicating whether the command signifies an exit
     */
    public Command(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Executes the command, making changes to the TaskList, Ui, and Storage as necessary.
     *
     * @param taskList TaskList to be modified by the command
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     * @throws Exception If an error occurs during command execution
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if the command signifies an exit.
     *
     * @return true if the command signifies an exit, false otherwise
     */
    public boolean isExit() {
        return this.isDone;
    }

}
