package duke.exceptions;

import duke.command.Command;

public class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InputException exceptionCommandProcessing(Command command, String input, Throwable cause) {
        String message = "Something went wrong when processing your " + command + "command: \n"
        + "Check your input again: " + input;
        return new InputException(message, cause);
    }
}