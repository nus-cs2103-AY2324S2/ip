package damon.task;

/**
 * Represents a FixedDuration task by description, status, and duration of task.
 */
public class FixedDuration extends Task {

    protected String duration;

    /**
     * Constructs a new FixedDuration object
     * with String description parameter and String duration parameter.
     *
     * @param description Description of FixedDuration.
     * @param duration Duration of FixedDuration.
     */
    public FixedDuration(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Constructs a new FixedDuration object with String description parameter,
     * boolean isDone parameter, and String duration parameter.
     *
     * @param description Description of FixedDuration.
     * @param isDone Status of FixedDuration.
     * @param duration Duration of FixedDuration.
     */
    public FixedDuration(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    /**
     * Returns a sentence containing icon, status, description, and duration of FixedDuration.
     *
     * @return Sentence representing FixedDuration.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString()
                + " (needs: " + duration + ")";
    }
}