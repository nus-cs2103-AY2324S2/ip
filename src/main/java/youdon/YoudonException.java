package youdon;

/**
 * Represents custom exceptions for the Youdon chatbot application.
 */
public class YoudonException extends Throwable {

    /**
     * Detects if the input contains a missing task description and throws an EmptyDescException if found.
     *
     * @param input The input string to check for missing task description.
     * @throws EmptyDescException if the task description is empty.
     */
    public static void detectMissingDesc(String input) throws EmptyDescException {
        if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
            throw new YoudonException.EmptyDescException("Hey! The task description is empty!");
        }
    }

    /**
     * Detects if the input contains an invalid command and throws an InvalidCommandException if found.
     *
     * @param input The input string to check for invalid command.
     * @throws InvalidCommandException if the command is invalid.
     */
    public static void detectInvalidCommand(String input) throws InvalidCommandException {
        for (ValidCommands command: ValidCommands.values()) {
            if (input.contains(command.getCommand())) {
                return;
            }
        }
        throw new YoudonException.InvalidCommandException("Sorry, I do not recognise that command.");
    }

    /**
     * Represents an exception thrown when the task description is empty.
     */
    public static class EmptyDescException extends Exception {

        /**
         * Constructs an EmptyDescException with the specified error message.
         *
         * @param errorMessage The error message explaining the empty description.
         */
        public EmptyDescException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Represents an exception thrown when an invalid command is detected.
     */
    public static class InvalidCommandException extends Exception {

        /**
         * Constructs an InvalidCommandException with the specified error message.
         *
         * @param errorMessage The error message explaining the invalid command.
         */
        public InvalidCommandException(String errorMessage) {
            super(errorMessage);
        }
    }
}
