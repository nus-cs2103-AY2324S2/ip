package exception;

/**
 * EmptyTimeException are Exceptions when user did not
 * input any date and time for the task.
 */
public class EmptyTimeException extends Exception {
    private String task;
    private String time;

    /**
     * The constructor of EmptyTimeException.
     *
     * @param task Task name.
     * @param time Time.
     */
    public EmptyTimeException(String task, String time) {
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
        return " buzz buzz~~ Please provide the "
                + time + " time for the "
                + task + ".\n";
    }
}
