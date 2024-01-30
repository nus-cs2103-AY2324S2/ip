package Exceptions;

public class InvalidInput extends Exception {
    public InvalidInput(String errorMessage) {
        super(errorMessage);
    }
}