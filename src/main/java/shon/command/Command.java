package shon.command;

import java.time.format.DateTimeParseException;

import shon.exception.ParameterException;

/**
 * Represents a command for Shon bot.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @return The result of the command.
     * @throws ParameterException If parameters provided to the command are missing or take invalid values.
     * @throws DateTimeParseException If the command requires a datetime parameter but it is invalid
     *     or in the wrong format.
     */
    public abstract String execute() throws ParameterException, DateTimeParseException;
}
