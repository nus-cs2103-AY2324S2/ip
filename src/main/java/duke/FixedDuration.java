package duke;

/**
 * Represents a fixed duration task in the Duke application.
 * A fixed duration task is an unscheduled task with a fixed duration.
 *
 * @author Qin Boan
 */
public class FixedDuration extends Task {

    protected String duration;

    /**
     * Constructs a fixed duration task with the given description and duration.
     *
     * @param description The description of the task.
     * @param duration    The fixed duration of the task.
     */
    public FixedDuration(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Returns a string representation of the fixed duration task.
     *
     * @return A string representation of the fixed duration task.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (for: " + this.duration + ")";
    }
}
