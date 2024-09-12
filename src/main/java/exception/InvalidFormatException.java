package exception;

/**
 * InvalidFormatException are Exceptions when user's input
 * is not a valid format.
 */
public class InvalidFormatException extends DukeException {
    private String task;
    private String time;

    /**
     * The constructor of InvalidFormatException.
     *
     * @param task Task name.
     * @param time Time.
     */
    public InvalidFormatException(String task, String time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Returns the exception message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return "buzz buzz~~ Please provide the time for the "
                + this.task
                + " by adding it after \""
                + this.time + "\".\n";
    }
}
