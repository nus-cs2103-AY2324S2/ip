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
}
