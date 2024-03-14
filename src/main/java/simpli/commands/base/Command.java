package simpli.commands.base;

import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;

/**
 * Represents a command with that can be executed.
 */
public interface Command {
    /**
     * Executes the command and returns the outcome.
     *
     * @param tokens which is parsed from the command String.
     * @return outcome of the execution.
     * @throws CommandException If an error occurs when command is executing.
     * @throws TaskException If an error occurs when an invalid task is being added.
     */
    public CommandResult execute(java.lang.String[] tokens) throws CommandException, TaskException;
}
