package cruisey.task;

/**
 * The {@code TaskStatus} enum represents the status of a task, indicating whether it is done or not done.
 * Each status has an associated code for storage purposes.
 */
public enum TaskStatus {
    /**
     * Represents a task that is marked as done.
     */
    DONE("1"),
    /**
     * Represents a task that is not yet done.
     */
    NOT_DONE("0");

    private final String code;

    /**
     * Constructs a {@code TaskStatus} enum with the given code.
     *
     * @param code The code associated with the task status.
     */
    TaskStatus(String code) {
        this.code = code;
    }

}
