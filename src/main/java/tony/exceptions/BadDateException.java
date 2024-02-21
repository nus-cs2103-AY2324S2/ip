package tony.exceptions;
/**
 * This Exception class represents exceptions thrown for bad user input for Date,
 */
public class BadDateException extends BadInputException {
    public BadDateException() {
        super("That ain't the right format for Date Time yo, yyyy-MM-ddThh:mm pls!");
    }
}
