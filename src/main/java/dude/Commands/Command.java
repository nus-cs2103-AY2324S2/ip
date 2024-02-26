package dude.Commands;

import dude.Exceptions.DudeException;

/**
 * The Command class represents a command that can be executed by the user.
 */
public abstract class Command {

    private final String format;
    private final String regex;

    /**
     * Constructor for the Command class.
     *
     * @param format The format of the command.
     * @param regex  The regex of the command.
     */
    protected Command(String format, String regex) {
        this.format = format;
        this.regex = regex;
    }

    /**
     * Executes the command.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    public abstract String execute() throws DudeException;

    /**
     * Gets the format of the command.
     *
     * @return The format of the command.
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * Gets the regex of the command.
     *
     * @return The regex of the command.
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     * Gets the type of the command.
     *
     * @return The type of the command.
     */
    public abstract CommandTypes getCommandType();
}
