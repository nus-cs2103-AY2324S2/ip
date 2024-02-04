package task;

import helpers.DateFormatter;

/**
 * Represents an event, which is a specialized form of task.Task.
 * This class adds specific start and end times to the task description.
 */
public class Event extends Task {
    private static final String EVENT_PREFIX = "[E]";
    private final String from;
    private final String to;

    /**
     * Constructs a new task.Event with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event as a String.
     * @param to The end time of the event as a String.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateFormatter.formatDate(from.trim());
        this.to = DateFormatter.formatDate(to.trim());
    }

    @Override
    public String convertTaskToFileString() {
        return String.format("E|%s|%s|%s|%s", super.getIsDone() ? "1" : "0", this.description.trim(), this.from, this.to);
    }

    /**
     * Returns a string representation of the task.Event.
     * The string includes the task.Event identifier, followed by the task.Task's string representation,
     * and the specified start and end times.
     */
    @Override
    public String toString() {
        return EVENT_PREFIX + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
