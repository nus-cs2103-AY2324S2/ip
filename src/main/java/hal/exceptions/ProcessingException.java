package hal.exceptions;

import hal.command.Command;

/**
 * The `ProcessingException` class represents an exception related to processing tasks or data in the Duke application.
 */
public class ProcessingException extends HalException {
    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an "ProcessingException" for command execution with a specific command and cause.
     *
     * @param command The command being executed when the exception occurred.
     * @param cause The cause of the exception (a Throwable object).
     * @return A `ProcessingException` with a message describing the error during command execution.
     */
    public static ProcessingException exceptionCommandExecution(Command command, Throwable cause) {
        String message = String.format(
                "Something went wrong when executing your %s command, check your input again",
                command);
        return new ProcessingException(message, cause);
    }
}
