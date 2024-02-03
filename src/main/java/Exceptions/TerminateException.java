package Exceptions;

public class TerminateException extends RuntimeException{
    public TerminateException(String errorMessage) {
        super(errorMessage);
    }
}
