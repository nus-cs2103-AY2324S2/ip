package exception;

/**
 * NarutoException is an abstract class that extends the Exception class.
 * It represents a custom exception in the Naruto application.
 */
public abstract class NarutoException extends Exception {

    /**
     * Enum representing the different types of errors that can occur in the Naruto application.
     */
    public enum ErrorType {
        EMPTY_COMMAND,
        EMPTY_DEADLINE,
        EMPTY_EVENT,
        INVALID_DEADLINE,
        INVALID_EVENT,
        INVALID_COMMAND,
        INVALID_INDEX,
        FILE_CORRUPTED,
        INVALID_ACTION,
        DUPLICATE_TASK,
        EMPTY_LIST
    }

    /**
     * Constructs a NarutoException with the specified error message.
     *
     * @param message the error message
     */
    public NarutoException(String message) {
        super(message);
    }

    /**
     * Returns the type of error associated with this exception.
     *
     * @return the error type
     */
    public abstract ErrorType getErrorType();

    /**
     * Creates a NarutoException for an empty command.
     *
     * @return the NarutoException for an empty command
     */
    public static NarutoException createEmptyCommandException() {
        return new EmptyCommandException("Looks like you forgot to enter anything after your "
                + "command! \nEnter an input after the command next time.");
    }

    /**
     * Creates a NarutoException for an empty deadline.
     *
     * @return the NarutoException for an empty deadline
     */
    public static NarutoException createEmptyDeadlineException() {
        return new EmptyDeadlineException("Looks like your deadline is missing a description! "
                + "\nEnter an input after the 'deadline' command to add a deadline.");
    }

    /**
     * Creates a NarutoException for an empty event.
     *
     * @return the NarutoException for an empty event
     */
    public static NarutoException createEmptyEventException() {
        return new EmptyEventException("Looks like your event is missing a description! \nEnter "
                + "an input after the 'event' command to add an event.");
    }

    /**
     * Creates a NarutoException for an invalid deadline.
     *
     * @return the NarutoException for an invalid deadline
     */
    public static NarutoException createInvalidDeadlineException() {
        return new InvalidDeadlineException("Looks like you forgot to enter a deadline! \nEnter a"
                + " deadline after the command '/by' to indicate when your deadline is.");
    }

    /**
     * Creates a NarutoException for an invalid event.
     *
     * @return the NarutoException for an invalid event
     */
    public static NarutoException createInvalidEventException() {
        return new InvalidEventException("Looks like your event doesn't have a valid start or "
                + "end time! \nMake sure your input uses the '/from' and '/to' commands to indicate"
                + "\nthe start and end times of your event.");
    }

    /**
     * Creates a NarutoException for an invalid command.
     *
     * @return the NarutoException for an invalid command
     */
    public static NarutoException createInvalidCommandException() {
        return new InvalidCommandException("Sorry, I didn't catch that... Try entering a valid "
                + "command!");
    }

    /**
     * Creates a NarutoException for an invalid index.
     *
     * @return the NarutoException for an invalid index
     */
    public static NarutoException createInvalidIndexException() {
        return new InvalidIndexException("Sorry, I couldn't find the task you indicated... "
                + "Make sure you enter a valid task number!");
    }

    /**
     * Creates a NarutoException for a corrupted file.
     *
     * @return the NarutoException for a corrupted file
     */
    public static NarutoException createFileCorruptedException() {
        return new FileCorruptedException("Oh no! Something seems to have happened to the "
                + "tasklist... I'll reset the tasks!");
    }

    /**
     * Creates a NarutoException for an invalid action.
     *
     * @return the NarutoException for an invalid action
     */
    public static NarutoException createInvalidActionException() {
        return new InvalidActionException("I couldn't seem to understand that... Try again!");
    }

    /**
     * Creates a new instance of NarutoException with a duplicate task error message.
     *
     * @return a NarutoException with a duplicate task error message
     */
    public static NarutoException createDuplicateTaskException() {
        return new DuplicateTaskException("You already added that task... Try adding "
                + "something else!");
    }

    /**
     * Creates a new instance of NarutoException with an empty list error message.
     *
     * @return a NarutoException with an empty list error message
     */
    public static NarutoException createEmptyListException() {
        return new EmptyListException("You don't have any tasks!");
    }
}
