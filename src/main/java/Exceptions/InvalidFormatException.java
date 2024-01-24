package Exceptions;

import Tasks.Task;

public class InvalidFormatException extends InvalidInputException {
    public InvalidFormatException(String message) {
        super(message);
    }
    public static void callInvalidFormatException(ErrorType task) throws InvalidFormatException {
        switch (task) {
            case TODO:
                throw new InvalidFormatException("   To record your task, enter:\n" +
                        "   todo <task>");
            case DEADLINE:
                throw new InvalidFormatException("   To set a deadline, enter:\n" +
                        "   deadline <task> /by <deadline>\n");
            case EVENT:
                throw new InvalidFormatException("   To record an event, enter:\n" +
                        "   event <event> /from <date and time> /to <date and time>\n");

        }
    }
}
