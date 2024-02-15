package Luke;

/**
 * Custom exception class for Luke program.
 * <p>
 * Represents exceptions that can occur during the execution of the Luke program.
 * </p>
 */
public class LukeException extends Exception {
    /**
     * Enumeration of exception types.
     */
    public enum ExceptionType {

        commandInvalid,
        taskNumberInvalid,
        descriptionEmpty,
        deadlineEmpty,
        deadlineWrongFormat,
        eventWrongFormat,
        eventFromEmpty,
        eventToEmpty
    }

    private ExceptionType type;

    /**
     * Constructs a LukeException object with the specified exception type.
     *
     * @param type the type of exception
     */
    public LukeException(ExceptionType type) {
        super();
        this.type = type;
    }

    /**
     * Constructs a default LukeException if no type is given object.
     */
    public LukeException() {
        super();
    }

    /**
     * Returns a message describing the exception.
     *
     * @return a message describing the exception
     */
    @Override
    public String getMessage() {
        return switch (type) {
            case commandInvalid -> "Invalid command/task type.";
            case taskNumberInvalid -> "Task does not exist. Please give a valid task number.";
            case descriptionEmpty -> "Invalid command. The description cannot be empty.";
            case deadlineEmpty -> "Invalid command. The deadline cannot be empty.";
            case deadlineWrongFormat -> "Invalid command. The deadline format as follows needs to be followed: " +
                    "deadline (description) /by (deadline).";
            case eventWrongFormat -> "Invalid command. The deadline format as follows needs to be followed: " +
                    "event (description) /from (start) /to (end).";
            case eventFromEmpty -> "Invalid command. The from section cannot be empty.";
            case eventToEmpty -> "Invalid command. The to section cannot be empty.";
            default -> this.toString();
        };
    }
}
