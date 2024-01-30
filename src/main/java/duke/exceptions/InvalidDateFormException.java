package duke.exceptions;

public class InvalidDateFormException extends Exception {
    public InvalidDateFormException() {
        super("OOPS!!! The date format is invalid. Either enter yyyy-mm-dd or yyyy-mm-dd HHmm :-(");
    }

    public InvalidDateFormException(String message) {
        super(message);
    }
}
