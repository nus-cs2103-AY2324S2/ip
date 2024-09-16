package badgpt.exceptions;

import badgpt.commands.Command;

/**
 * Signals that the command was entered using an invalid format.
 */
public class WrongFormatException extends CommandException {
    private String rightUsage = "Please type in the command as follows: ";
    private String example;

    /**
     * Creates a new WrongFormatException with the specified message and command. The proper usage of the command along
     * with an example will also be displayed.
     *
     * @param message The error message.
     * @param cmd The command which caused the error.
     */
    public WrongFormatException(String message, Command cmd) {
        super(message);
        rightUsage += cmd.getRightUsage();
        example = cmd.getExample();
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return rightUsage + "Example usage: " + example;
    }
}
