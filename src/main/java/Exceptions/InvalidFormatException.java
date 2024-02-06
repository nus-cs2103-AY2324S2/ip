package Exceptions;

/**
 * The InvalidFormatException class represents an exception that is thrown when a data format is
 * incompatible with the expected format.
 */
public class InvalidFormatException extends InvalidInputException {
    public InvalidFormatException(String message) {
        super(message);
    }

    /**
     * Throws InvalidFormatException with different error messages based on the task type.
     *
     * @param task type of task with the wrong format.
     * @throws InvalidFormatException Thrown with different error messages depending on the task type.
     */
    public static void callInvalidFormatException(ErrorType task) throws InvalidFormatException {
        switch (task) {
            case TODO:
                throw new InvalidFormatException("   To record your task, enter:\n" +
                        "   todo <task>");
            case DEADLINE:
                throw new InvalidFormatException("   To set a deadline, enter:\n" +
                        "   deadline <task> /by <YYYY-MM-DD HH:mm>\n");
            case EVENT:
                throw new InvalidFormatException("   To record an event, enter:\n" +
                        "   event <event> /from <date and time> /to <date and time>\n");

        }
    }
}
