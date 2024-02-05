package task;

public class Events extends Task {

    protected String from;

    protected String to;

    /**
     * Constructs a new Events task with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

    }

    /**
     * Overrides the toString method to provide a formatted representation of the event task.
     *
     * @return The formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from  + " to: " + to + ")";
    }
}
