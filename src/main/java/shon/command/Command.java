package shon.command;

import java.time.format.DateTimeParseException;

import shon.exception.ParameterException;

/**
 * Represents a command for Shon bot.
 */
public abstract class Command {
    public abstract String execute() throws ParameterException, DateTimeParseException;
}
