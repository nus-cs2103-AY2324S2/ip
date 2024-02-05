package task;
/** This class represents a deadline where the User can specify when a task would end by*/
public class Deadline extends Task {
    /** The field for when teh task ends by*/
    protected String by;

    /**
     * Constructs a deadline object that takes the task as per the description and when the task
     * ends by
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }
    /**
     * Return the String text that displays the task , when it ends by and with
     * the relevant tag
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toDataFormat() {
        String isDone = this.isDone ? "1 | " : "0 | ";
        return "D | " + isDone + this.description + " | " + this.by;
    }
}
