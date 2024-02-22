package simpli.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task to know the start and end date and time of the event.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initializes an event task with the specified attributes.
     *
     * @param isDone boolean representing if a task is completed or not.
     * @param name String.
     * @param from Starting date and time of an event.
     * @param to Ending date and time of an event.
     */
    public Event(boolean isDone, String name, LocalDateTime from, LocalDateTime to) {
        super(isDone, name);

        assert from.isAfter(LocalDateTime.now());
        assert to.isAfter(from);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the event task as a comma-separated values (csv) String representation.
     *
     * @return String representing the csv value for the event task.
     */
    @Override
    public String toCsv() {
        return String.format("Event,%s,%s,%s", super.toCsv(),
                this.from.format(FORMATTER), this.to.format(FORMATTER));
    }

    /**
     * Returns the event task String representation.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(FORMATTER), this.to.format(FORMATTER));
    }
}
