package duke;

import java.time.LocalDate;
import java.util.Date;

/**
 * Represents a task that is an event in the task list.
 * Subclass of the Task class.
 */
public class Event extends Task {
    /**
     * The starting date of the event.
     */
    protected LocalDate from;
    /**
     * The ending date of the event.
     */
    protected LocalDate to;
    /**
     * Constructs an Event object with the given description and event duration.
     *
     * @param description The description of the task.
     * @param from        The starting date of the event.
     * @param to          The ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description, Task.TaskType.EVENT);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    /**
     * Returns the type of the task (event).
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }
    /**
     * Returns a formatted string for writing the Event object to a file.
     *
     * @return A formatted string for writing the Event object to a file.
     */
    public String toFileString() {
        return String.format("%s |  %d | %s | %s | %s", getType(), isDone ? 1 : 0, description, from, to);
    }
    @Override
    public LocalDate getDate() {
        return from;
    }
}
