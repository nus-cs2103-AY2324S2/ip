package commands;

import exceptions.InvalidInputFormatException;
import utils.TaskList;

/**
 * The Command class represents a command that can be executed by the program.
 * Subclasses of Command implement specific actions.
 */
public abstract class Command {
    private boolean isExit;
    private String response;

    /**
     * Constructs a Command object with isExit set to false by default.
     */
    Command() {
        this.isExit = false;
    }

    Command(String response) {
        this.isExit = false;
        this.response = response;
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    protected void setIsExit() {
        isExit = true;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    /**
     * Executes the command.
     *
     * @param taskList                      The task list object.
     * @throws InvalidInputFormatException  If the input format is invalid.
     */
    public abstract void execute(TaskList taskList) throws InvalidInputFormatException;
}
