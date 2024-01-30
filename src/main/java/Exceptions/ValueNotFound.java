package Exceptions;

public class ValueNotFound extends Exception {
    public ValueNotFound(String errorMessage) {
        super(errorMessage);
    }
}