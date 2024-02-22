package exception;

/**
 * InvalidDateTimeException are Exceptions when user's inputted
 * date and time is not a valid format
 */
public class InvalidDateTimeException extends DukeException {

    private String message;

    /**
     * The constructor of InvalidDateTimeException.
     *
     * @param message The error message.
     */
    public InvalidDateTimeException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        String str;
      
        if (message.equals("unexpected date")) {
            str = "buzz buzz~~ The start date cannot be later than the end date.\n";
        } else if (message.equals("unexpected time")) {
            str = "buzz buzz~~ The start time cannot be later than the end time.\n";
        } else {
            str = "buzz buzz~~ Please provide the time in this format: 'yyyy-mm-dd hh:mm'\n";
        }
      
        return str;
    }
}
