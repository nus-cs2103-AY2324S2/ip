package luke;

public class LukeException extends Exception {
    public enum ExceptionType {

        commandInvalid,
        taskNumberInvalid,
        descriptionEmpty,
        deadlineEmpty,
        deadlineWrongFormat,
        eventWrongFormat,
        eventFromEmpty,
        eventToEmpty,
        findKeywordEmpty
    }

    private ExceptionType type;

    public LukeException(ExceptionType type) {
        super();
        this.type = type;
    }

    public LukeException() {
        super();
    }

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
            case findKeywordEmpty -> "Invalid command. The find command must be followed by a keyword.";
            default -> this.toString();
        };
    }
}
