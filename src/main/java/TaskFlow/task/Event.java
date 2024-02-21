package TaskFlow.task;

/**
 * Represents an event task in the Duke chatbot application.
 * It is a subclass of the Task class.
 */
public class Event extends Task {
    private String from;

    private String to;

    /**
     * Constructs an Event object with the specified description, start, and end times.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " | " + from + "-" + to;
    }
}
