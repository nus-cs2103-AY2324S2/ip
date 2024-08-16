package remi.model;

/**
 * Represents an event class that starts and ends at a certain date. Subclass of Task.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Simple constructor for the event. Formats the dates according to the formatDate() method.
     * Works with dates only, not time.
     *
     * @param label name of the task
     * @param from starting date
     * @param to ending date
     */
    public Event(String label, String from, String to) {
        super(label);
        this.from = formatDate(from);
        this.to = formatDate(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    /**
     * Returns a parsable string of the event task. Meant to be used for remi.storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    @Override
    public String getParsableString() {
        return String.format("E|%s|%s|%s", super.getParsableString(), from, to);
    }
}
