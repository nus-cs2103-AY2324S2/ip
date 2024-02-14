package task;

public class InvalidDateException extends Exception {
    public InvalidDateException (String error) {
        super(error);
    }
}
