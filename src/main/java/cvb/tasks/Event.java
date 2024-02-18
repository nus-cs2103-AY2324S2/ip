package cvb.tasks;

import java.time.LocalDate;

import cvb.utils.DateTime;

/**
 * The {@code Event} class represents a task that spans a specific time period.
 * It extends the {@code Task} class and includes functionality to handle events.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new {@code Event} instance with the specified description and time period.
     *
     * @param description the description of the event
     * @param from        the start date of the event
     * @param to          the end date of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        this(description, false, from, to);
    }

    /**
     * Constructs a new {@code Event} instance with the specified description, completion status, and time period.
     *
     * @param description the description of the event
     * @param isDone      the completion status of the event
     * @param from        the start date of the event
     * @param to          the end date of the event
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the {@code Event} object to a string format suitable for saving to a file.
     *
     * @return a formatted string representing the event for file storage
     */
    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | "
                      + DateTime.dateToFile(this.from) + " | "
                      + DateTime.dateToFile(this.to);
    }

    /**
     * Returns a string representation of the {@code Event} object.
     *
     * @return a formatted string representing the event for display
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                     + DateTime.dateToString(this.from) + " to: "
                     + DateTime.dateToString(this.to) + ")";
    }
}
