package jav.command;

/**
 * Command handles the execution of a given command by the user.
 */
public abstract class Command {
    /**
     * Executes the given command and returns the result.
     *
     * @return String the result of the command.
     * @throws Exception if invalid command.
     */
    public abstract String execute() throws Exception;

    /**
     * Undoes the excution of the command.
     *
     * @return String the result of the undo or null if there is nothing to undo.
     * @throws Exception if invalid undo.
     */
    public abstract String undo() throws Exception;
}
