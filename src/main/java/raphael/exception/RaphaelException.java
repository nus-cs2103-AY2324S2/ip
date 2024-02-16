package raphael.exception;

import raphael.command.Command;

/**
 * The exception class for Raphael.
 */
public class RaphaelException extends Exception {
    /**
     * The enumeration used to identify type of exception.
     */
    public enum Type {
        INVALID_TASK_INDEX("Invalid task index!"),
        READ_IO_EXCEPTION("Failed to read from the task file!"),
        WRITE_IO_EXCEPTION("Failed to write to the task file!"),
        DUPLICATE_TASK_EXCEPTION("Duplicate task detected!");
        private final String errorMessage;
        private Type(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
    public static final String EXECUTE_EXIT_COMMAND = "Exit command can't be executed!";
    public static final String CONNECT_FILE_EXCEPTION = "Error occurred when connecting the bot with the file!";
    public RaphaelException(String message) {
        super(message);
    }

    public RaphaelException(Type exceptionType) {
        super(exceptionType.errorMessage);
    }
    /**
     * Returns the message for correct command format based on the command type.
     *
     * @param commandType the type of command.
     * @return the error message.
     */
    public static String invalidFormat(Command.Type commandType) {
        return String.format("Please follow the format!:\n\t%s", commandType.getFormat());
    }
}
