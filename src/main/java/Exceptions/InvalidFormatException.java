package Exceptions;

public class InvalidFormatException extends InvalidInputException {
    public InvalidFormatException(String message) {
        super(message);
    }
    public static void callInvalidFormatException(String task) throws InvalidFormatException {
        switch (task) {
            case "todo":
                throw new InvalidFormatException("   To record your task, enter:\n" +
                        "   todo <task>");
            case "deadline":
                throw new InvalidFormatException("   To set a deadline, enter:\n" +
                        "   deadline <task> /by <deadline>\n");
            case "event":
                throw new InvalidFormatException("   To record an event, enter:\n" +
                        "   event <event> /from <date and time> /to <date and time>\n");

        }
    }
}
