package hal.exceptions;

import hal.command.Command;

/**
 * The `InputException` class represents an exception related to input processing.
 */
public class InputException extends HalException {
    public InputException(String message) {
        super(message);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an `InputException` for command parsing with a specific command, input, and cause.
     *
     * @param command The command being parsed when the exception occurred.
     * @param input The input associated with the exception.
     * @param cause The cause of the exception (a Throwable object).
     * @return An `InputException` with a message describing the error during command parsing.
     */
    public static InputException exceptionCommandParsing(Command command, String input, Throwable cause) {
        String message = String.format(
                "Something went wrong when parsing your %s command, check your input again:\n%s",
                command, input);
        return new InputException(message, cause);
    }
}
