package chatbro;

/**
 * Represents a IntervalDeadline task that has a deadline that spans over a period of time.
 */
public class IntervalDeadline extends Task {
    protected String startInterval;
    protected String endInterval;

    /**
     * Constructor for IntervalDeadline class.
     *
     * @param description Description of IntervalDeadline object.
     * @param startInterval the starting time of the IntervalDeadline object.
     * @param endInterval the ending time of the IntervalDeadline object.
     */
    public IntervalDeadline(String description, String startInterval, String endInterval) {
        super(description);
        type = "I";
        this.startInterval = startInterval;
        this.endInterval = endInterval;
    }
    public IntervalDeadline(String description, String startInterval, String endInterval, boolean isDone) { // Overloaded constructor: loading from file
        super(description, isDone);
        type = "I";
        this.startInterval = startInterval;
        this.endInterval = endInterval;
    }
    public String toString() {
        return "[I]" + super.toString() + Ui.dueByInterval() + this.startInterval + Ui.to() + this.endInterval + ")";
    }
    @Override
    public String toStorageFormat() {
        return super.toStorageFormat() + ";;" + this.startInterval + ";;" + this.endInterval;
    }

}
