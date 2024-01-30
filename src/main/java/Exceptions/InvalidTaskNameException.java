package Exceptions;

public class InvalidTaskNameException extends Exception {
    public InvalidTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
