package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

/**
 * Represents an abstract command that can be executed on an {@link ActivityList}.
 */
public abstract class Command {

    /**
     * The input string associated with the command.
     */
    final String input;

    /**
     * Constructor to initialize a command with the given input.
     *
     * @param input The input string associated with the command.
     */
    Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command on the specified {@link ActivityList}.
     *
     * @param list The activity list on which the command should be executed.
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract void execute(ActivityList list) throws CommandException;

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string representing the result or output of the command execution.
     */
    @Override
    public abstract String toString();
}
