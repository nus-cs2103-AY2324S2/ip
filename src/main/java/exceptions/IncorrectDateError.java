package exceptions;

/**
 *  Represents exception relating to user inputting an incorrect date format
 *  Correct date formats are of forms d/M/yyyy HHmm, yyyy-MM-dd HH:mm or dd MMM yyyy h:mm a only
 */
public class IncorrectDateError extends Exception {
    public IncorrectDateError() {
        super();
    }

    public IncorrectDateError(String message) {
        super(message);
    }
}
