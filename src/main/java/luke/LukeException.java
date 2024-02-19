package luke;

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
        listCommandInvalid,
        taskNumberInvalid,
        descriptionEmpty,
        deadlineEmpty,
        deadlineWrongFormat,
        eventWrongFormat,
        eventFromEmpty,
        eventToEmpty,
        findKeywordEmpty,
        editDetailsEmpty,
        editIncorrectNoArguments,
        editFieldInvalid
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
        case listCommandInvalid -> "Invalid list command. To list all tasks, please enter 'list' .";
        case taskNumberInvalid -> "Task does not exist. Please give a valid task number.";
        case descriptionEmpty -> "Invalid command. The description cannot be empty.";
        case deadlineEmpty -> "Invalid command. The deadline cannot be empty.";
        case deadlineWrongFormat -> "Invalid command. The deadline format as follows needs to be followed: "
                + "deadline (description) /by (deadline).";
        case eventWrongFormat -> "Invalid command. The deadline format as follows needs to be followed: "
                + "event (description) /from (start) /to (end).";
        case eventFromEmpty -> "Invalid command. The from section cannot be empty.";
        case eventToEmpty -> "Invalid command. The to section cannot be empty.";
        case findKeywordEmpty -> "Invalid command. The find command must be followed by a keyword.";
        case editDetailsEmpty -> "Invalid command. The edit command must be followed by 3 arguments: task number"
                + ", the field to edit, and the new string.";
        case editIncorrectNoArguments -> "Invalid command. The edit command must have 3 arguments, each separated"
                + "by a space: task number, field to edit, and the new string.";
        case editFieldInvalid -> "Invalid command. The task you select does not have the field that you "
                + "indicated.\n The possible fields are: 'description', 'by', 'from' and 'to'.";
        };
    }
}
