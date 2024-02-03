package raphael.exception;

import raphael.command.Command;
public class RaphaelException extends Exception {
    public enum TYPE {
        INVALID_TASK_INDEX("Invalid task index!"),
        READ_IO_EXCEPTION("Failed to read from the task file!"),
        WRITE_IO_EXCEPTION("Failed to write to the task file!");
        private final String errorMessage;
        private TYPE(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
    public static final String EXECUTE_EXIT_COMMAND = "Exit command can't be executed!";
    public static final String CONNECT_FILE_EXCEPTION = "Error occurred when connecting the bot with the file!";
    public RaphaelException(String message) {
        super(message);
    }

    public RaphaelException(RaphaelException.TYPE exceptionType) {
        super(exceptionType.errorMessage);
    }
    /**
     * Returns the message for correct command format based on the command type.
     *
     * @param commandType the type of command.
     * @return the error message.
     */
    public static String invalidFormat(Command.TYPE commandType) {
        return String.format("Please follow the format!:\n\t%s", commandType.getFormat());
    }
}
