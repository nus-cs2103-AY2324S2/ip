package tony.exceptions;

public class BadDateException extends BadInputException {
    public BadDateException() {
        super("That ain't the right format for Date Time yo, yyyy-MM-ddThh:mm pls!");
    }
}
