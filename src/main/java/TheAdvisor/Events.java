package theadvisor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an event task, with a description of the task, when it starts, and when it ends
 * Extends the Task class and implements Serializable for persistence.
 */
public class Events extends Task implements Serializable {
    protected final LocalDateTime startTime;
    protected final LocalDateTime endTime;

    /**
     * Constructs a new Events instance with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Events(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert startTime != null : "Start time cannot be null";
        assert endTime != null : "End time cannot be null";
        assert !endTime.isBefore(startTime) : "End time cannot be before start time";
        this.startTime = startTime;
        this.endTime = endTime;
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
        assert getDescription().contains("|") : "Description should contain the event times";
        return this.description + " | " + startTime + " " + endTime;
    }

    /**
     * Gets a string representation of the event task.
     *
     * @return A string representation of the event task, including type, status, description, and time frame.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(Task.OUTPUT_FORMAT) + "hrs to: "
                + endTime.format(Task.OUTPUT_FORMAT) + "hrs)";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Events events = (Events) o;
        return Objects.equals(getDescription(), events.getDescription())
                && Objects.equals(startTime, events.startTime)
                && Objects.equals(endTime, events.endTime);
    }

}
