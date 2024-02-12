package aaron.exception;

/**
 * Class that represents an exception relating to invalid time format for a
 * deadline/event entered
 */
public class InvalidDateException extends InvalidCommandFormatException {
    public InvalidDateException(String e) {
        super(e);
    }
}
