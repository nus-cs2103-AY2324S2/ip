package Exceptions;

public class DuplicateTaskNameException extends IncorrectInputException {

    public DuplicateTaskNameException(String message) {
        super(message);
    }
}