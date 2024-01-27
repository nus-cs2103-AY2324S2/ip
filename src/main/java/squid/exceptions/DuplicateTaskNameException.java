package squid.exceptions;

public class DuplicateTaskNameException extends IncorrectInputException {

    public DuplicateTaskNameException(String message) {
        super(message);
    }
}