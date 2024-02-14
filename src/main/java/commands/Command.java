package commands;

import exception.InvalidCommandException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidIndexException;

/**
 * The Command interface defines the contract for executing different commands related to task management.
 * Implementing classes must provide the execute method to carry out the specific functionality of the command.
 */
public interface Command {
    /**
     * Executes the command, performing the associated task management action.
     *
     * @throws InvalidCommandException If the command is invalid or cannot be executed.
     * @throws InvalidDeadlineException If there is an issue with a deadline-related command.
     * @throws InvalidEventException If there is an issue with an event-related command.
     * @throws InvalidIndexException If there is an issue with an index-related command.
     */
    String execute() throws InvalidCommandException, InvalidDeadlineException, InvalidEventException,
            InvalidIndexException;
}
