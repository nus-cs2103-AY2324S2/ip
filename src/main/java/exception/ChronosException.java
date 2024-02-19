package exception;

/**
 * Represents the custom exceptions thrown in Chronos Task Management System.
 */
public abstract class ChronosException extends Exception {
    /**
     * Constructs a ChronosException with the specified error message.
     *
     * @param message Error message shown as exception is thrown.
     */
    public ChronosException(String message) {
        super(message);
    }

    /**
     * Constructs a InvalidByeException with the specified error message.
     *
     * @return InvalidByeException.
     */
    public static ChronosException createInvalidByeException() {
        return new InvalidByeException("Invalid command. To exit the program, please key in bye.");
    }

    /**
     * Constructs a InvalidListException with the specified error message.
     *
     * @return InvalidListException.
     */
    public static ChronosException createInvalidListException() {
        return new InvalidListException("Invalid command. To print the list of tasks, please key in list.");
    }

    /**
     * Constructs a InvalidHelpException with the specified error message.
     *
     * @return InvalidHelpException.
     */
    public static ChronosException createInvalidHelpException() {
        return new InvalidHelpException("Invalid command. To view the list of commands, please key in help.");
    }

    /**
     * Constructs a InvalidCommandException with the specified error message.
     *
     * @return InvalidCommandException.
     */
    public static ChronosException createInvalidCommandException() {
        return new InvalidCommandException("Invalid command. Please try again.");
    }

    /**
     * Constructs a MissingTaskNumberException with the specified error message.
     *
     * @return MissingTaskNumberException.
     */
    public static ChronosException createMissingTaskNumberException() {
        return new MissingTaskNumberException("Missing task number. Please specify the task number.");
    }

    /**
     * Constructs a MissingDescriptionException with the specified error message.
     *
     * @return MissingDescriptionException.
     */
    public static ChronosException createMissingDescriptionException() {
        return new MissingDescriptionException("Missing description. Please specify the description of your task.");
    }

    /**
     * Constructs a InvalidDeadlineException with the specified error message.
     *
     * @return InvalidDeadlineException.
     */
    public static ChronosException createInvalidDeadlineException() {
        return new InvalidDeadlineException("Invalid command. "
                + "Please include a task description and due date following the example below:\n"
                + "\t\te.g. deadline return library book /by 2024-09-22 15:00");
    }

    /**
     * Constructs a InvalidEventException with the specified error message.
     *
     * @return InvalidEventException.
     */
    public static ChronosException createInvalidEventException() {
        return new InvalidEventException("Invalid command. "
                + "Please include a task description and due date following the example below:\n"
                + "\t\te.g. event concert /from 2024-02-16 18:00 /to 2024-02-16 20:00");
    }

    /**
     * Constructs a KeywordNotFoundException with the specified error message.
     *
     * @return KeywordNotFoundException.
     */
    public static ChronosException createKeywordNotFoundException() {
        return new KeywordNotFoundException("Keyword not found in any tasks. Please specify a valid keyword.");
    }

    /**
     * Constructs a MissingKeywordException with the specified error message.
     *
     * @return MissingKeywordException.
     */
    public static ChronosException createMissingKeywordException() {
        return new MissingKeywordException("Missing keyword. Please specify at least 1 keyword.");
    }
}
