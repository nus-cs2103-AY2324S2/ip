package commands;

import exceptions.InvalidInputFormatException;
import utils.TaskList;
import utils.Ui;

/**
 * The Command class represents a command that can be executed by the program.
 * Subclasses of Command implement specific actions.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructs a Command object with isExit set to false by default.
     */
    Command() {
        this.isExit = false;
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets this command as an exit command.
     */
    protected void setIsExit() {
        isExit = true;
    }

    /**
     * Executes the command.
     *
     * @param ui       The user interface object.
     * @param taskList The task list object.
     * @throws InvalidInputFormatException If the input format is invalid.
     */
    public abstract void execute(Ui ui, TaskList taskList) throws InvalidInputFormatException;
}