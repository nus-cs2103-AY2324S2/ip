package shon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the <code>TaskList</code>.
 */
public class Event extends Task {
    /** The formatter for reading/storing the by datetime */
    private static DateTimeFormatter ioFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The formatter for displaying the by datetime */
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy HHmm");
    /** The from datetime of the task */
    private LocalDateTime from;
    /** The to datetime of the task */
    private LocalDateTime to;

    /**
     * Creates a new event with the given description, from datetime, to datetime, and completion status.
     *
     * @param description The description of the <code>Event</code> task.
     * @param from The from datetime of this <code>Event</code> task.
     * @param to The to datetime of this <code>Event</code> task.
     * @param isDone The completion status of the task.
     * @throws DateTimeParseException If the given by datetime does not adhere to the format of ioFormatter.
     */
    public Event(String description, String from, String to, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, ioFormatter);
        this.to = LocalDateTime.parse(to, ioFormatter);;
    }

    /**
     * Returns the string representation of the <code>Event</code>.
     *
     * @return [E] to indicate <code>Event</code>, completion status marked by X if done, task description,
     *     from datetime, and to datetime.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.from.format(displayFormatter), this.to.format(displayFormatter));
    }

    /**
     * Formats the data of the task to be stored. E represents <code>Event</code>, 0/1 to represent completion
     * status, task description, from datetime and to datetime with format of ioFormatter. Fields are separated by "|".
     *
     * @return The data of the task formatted to be stored.
     */
    @Override
    public String formatData() {
        return "E" + " | " + this.formatTask() + " | " + this.from.format(ioFormatter) + " | "
                + this.to.format(ioFormatter);
    }

    /**
     * Sets the display datetime format to the given pattern.
     *
     * @param pattern The pattern to set the display datetime to.
     */
    public static void setDisplayFormatter(String pattern) {
        Event.displayFormatter = DateTimeFormatter.ofPattern(pattern);
    }
}
