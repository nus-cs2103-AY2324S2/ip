package Exceptions;

public class LeluException extends Exception {
    public enum ErrorType {
        TODO,
        EVENT,
        DEADLINE,
        FIND
    }
    public LeluException(String message) {
        super(message);
    }
}
