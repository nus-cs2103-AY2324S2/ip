package Oak.exceptions;

import Oak.type.CommandEnum;

import java.util.Arrays;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends InvalidInputException {
        public InvalidCommandException(String invalidCommand) {
            super("Invalid Command: " + invalidCommand + " received.\n" +
                    "Only the following commands are accepted:\n" +
                    Arrays.toString(CommandEnum.values()));
        }
    }

    public static class InvalidFormatException extends InvalidInputException {
        public InvalidFormatException(String message, String command) {
            super("Invalid format for command " + command + " provided: " + message);
        }
    }
}