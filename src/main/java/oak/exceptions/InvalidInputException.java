package oak.exceptions;

import java.util.Arrays;

import oak.feedback.enums.CommandEnum;

/**
 * The type Invalid input exception.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Custom Exception for Invalid Command provided by user
     */
    public static class InvalidCommandException extends InvalidInputException {
        /**
         * Instantiates a new Invalid command exception.
         *
         * @param invalidCommand the invalid command
         */
        public InvalidCommandException(String invalidCommand) {
            super("Invalid Command: " + invalidCommand + " received.\n" +
                    "Only the following commands are accepted:\n" +
                    Arrays.toString(CommandEnum.values()));
        }
    }

    /**
     * Custom Exception for Invalid Format for a specific command provided by user
     */
    public static class InvalidFormatException extends InvalidInputException {
        /**
         * Instantiates a new Invalid format exception.
         *
         * @param message the error message
         * @param command the command the user inputted
         */
        public InvalidFormatException(String message, String command) {
            super("Invalid format for command " + command + " provided: " + message);
        }
    }
}
