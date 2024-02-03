package academicweapon.task;

/**
 * Represents an event task in the Duke application.
 * The Event class extends the Task class and includes a start and end time for the event.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * Overrides the toString method in the Task class.
     *
     * @return String representation of the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    /**
     * Returns a formatted string suitable for storing the Event task in a file
     * Overrides the fileToString method in the Task class
     *
     * @return Formatted string for storing the Event task in a file
     */
    @Override
    public String fileToString() {
        return "E | " + super.fileToString() + "| " + this.from.trim() + "-" + this.to;
    }


}
