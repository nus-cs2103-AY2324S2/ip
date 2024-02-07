package exception;

/**
 * Represents the custom exceptions thrown in Chronos Task Management System.
 */
public abstract class ChronosException extends Exception {
    private static final String DIVIDER = "        ------------------------------------------------------------";

    /**
     * Constructs a ChronosException with the specified error message.
     *
     * @param message Error message shown as exception is thrown.
     */
    public ChronosException(String message) {
        super(message);
    }

    /**
     * Constructs a InvalidListException with the specified error message.
     *
     * @return InvalidListException
     */
    public static ChronosException createInvalidListException() {
        return new InvalidListException(DIVIDER + "\n" + "        Invalid command. To print the list of tasks, please use list only.\n" + DIVIDER);
    }

    /**
     * Constructs a InvalidCommandException with the specified error message.
     *
     * @return InvalidCommandException
     */
    public static ChronosException createInvalidCommandException() {
        return new InvalidCommandException(DIVIDER + "\n" + "        Invalid command. Please try again.\n" + DIVIDER);
    }

    /**
     * Constructs a MissingTaskNumberException with the specified error message.
     *
     * @return MissingTaskNumberException
     */
    public static ChronosException createMissingTaskNumberException() {
        return new MissingTaskNumberException(DIVIDER + "\n" + "        Missing task number. Please specify the task number.\n" + DIVIDER);
    }

    /**
     * Constructs a MissingDescriptionException with the specified error message.
     *
     * @return MissingDescriptionException
     */
    public static ChronosException createMissingDescriptionException() {
        return new MissingDescriptionException (DIVIDER + "\n" + "        Missing description. Please specify the description of your task.\n" + DIVIDER);
    }

    /**
     * Constructs a InvalidDeadlineException with the specified error message.
     *
     * @return InvalidDeadlineException
     */
    public static ChronosException createInvalidDeadlineException() {
        return new InvalidDeadlineException (DIVIDER + "\n" + "        Invalid command. Please include a task name and a valid due date following the syntax of the example below:\n" +
                "        e.g. deadline return library book /by 2024-09-22 15:00\n" + DIVIDER);
    }

    /**
     * Constructs a InvalidEventException with the specified error message.
     *
     * @return InvalidEventException
     */
    public static ChronosException createInvalidEventException() {
        return new InvalidEventException (DIVIDER + "\n" + "        Invalid command. Please include a task name and a valid due date following the syntax of the example below:\n" +
                "        e.g. event concert /from 2024-02-16 18:00 /to 2024-02-16 20:00\n" + DIVIDER);
    }
}