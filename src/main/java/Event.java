/**
 * Represents an event task.
 */
public class Event extends Task {
    private final String from; // Event starting time
    private final String to; // Event ending time

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description A string describing the event.
     * @param from A string representing the start time of the event.
     * @param to A string representing the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description); // Calls the constructor of the superclass Task
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A formatted string with the type of task, its description,
     *         start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
