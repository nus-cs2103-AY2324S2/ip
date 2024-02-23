package friendlytool.task;

import friendlytool.process.Date;


/**
 * Class for managing deadline tasks.
 */
public class Deadline extends Task {
    private Date by;

    /**
     * Constructs a Deadline.
     *
     * @param name   name of the task
     * @param isDone completed or not.
     * @param by     due date.
     */
    public Deadline(String name, boolean isDone, Date by) {
        super(name, isDone);
        this.by = by;
    }


    /**
     * Converts a deadline to a readable format.
     *
     * @return string format of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts a deadline to a save format.
     *
     * @return save format of a deadline.
     */
    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " | " + by.toSaveFormat() + "\n";
    }

    @Override
    public Date getEndTime() {
        return by;
    }
}
