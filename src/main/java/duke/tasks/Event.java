package duke.tasks;

/**
 * Class represent Task type Event.
 */
public class Event extends Task {
    private String start;
    private String by;

    /**
     * Initializes an Event object with given params.
     * @param status True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     * @param start task start time.
     * @param by task end time.
     */
    public Event(Boolean status, String detail, String start, String by) {
        super(status, detail);
        this.start = start;
        this.by = by;
    }

    /**
     * Formats object to be stored in file.
     * @return Formatted string to be stored in file.
     */
    @Override
    public String inFileStringFormat() {
        return "E|" + super.inFileStringFormat() + "|" + this.start + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "  + start + " to: " + by + ")";
    }
}
