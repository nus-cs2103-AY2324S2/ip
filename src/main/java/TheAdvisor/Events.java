package TheAdvisor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an event task, with a description of the task, when it starts, and when it ends
 * Extends the Task class and implements Serializable for persistence.
 */
public class Events extends Task implements Serializable {
    protected final LocalDateTime start;
    protected final LocalDateTime end;

    /**
     * Constructs a new Events instance with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task, "E " for event.
     */
    @Override
    public String getType() {
        return "E ";
    }

    /**
     * Gets the description of the task, including start and end times.
     *
     * @return The description of the task with start and end times.
     */
    @Override
    public String getDescription() {
        return this.description + " | " + start + " " + end;
    }

    /**
     * Gets a string representation of the event task.
     *
     * @return A string representation of the event task, including type, status, description, and time frame.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(Task.outputFormat) + "hrs to: " +
                end.format(Task.outputFormat) + "hrs)";
    }
}