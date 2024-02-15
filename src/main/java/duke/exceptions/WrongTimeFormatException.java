package duke.exceptions;


/**
 * Exception class representing an error related to incorrect time format in Duke application.
 * This exception is thrown when there is an issue with the provided date and time format.
 */
public class WrongTimeFormatException extends Exception {

    /**
     * Constructs a WrongTimeFormatException with the specified detail message.
     *
     * @param message The detail message.
     */
    public WrongTimeFormatException(String string) {
        super(string);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message guiding the user to use the correct date and time format.
     */
    @Override
    public String getMessage() {
        return "    " + super.getMessage()
                + "\n    Try again using the format"
                + "\n     {day/month/year HHmm}"
                + "\n    or {date + H:mm pm/am}";
    }
}
