package shon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the <code>TaskList</code>.
 */
public class Deadline extends Task {
    /** The formatter for reading/storing the by datetime */
    private static DateTimeFormatter ioFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The formatter for displaying the by datetime */
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy HHmm");
    /** The by datetime of the task */
    private LocalDateTime by;

    /**
     * Creates a new Deadline with the given description, by datetime, and completion status.
     *
     * @param description The description of the <code>Deadline</code> task.
     * @param by The by datetime of this <code>Deadline</code> task.
     * @param isDone The completion status of the task.
     * @throws DateTimeParseException If the given by datetime does not adhere to the format of ioFormatter.
     */
    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, ioFormatter);
    }

    /**
     * Returns the string representation of the <code>Deadline</code>.
     *
     * @return [D] to indicate <code>Deadline</code>, completion status marked by X if done, task description,
     * and the due datetime.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(displayFormatter));
    }

    /**
     * Formats the data of the task to be stored. D represents <code>Deadline</code>, 0/1 to represent completion
     * status, task description and by datetime with format of ioFormatter. Fields are separated by "|".
     *
     * @return The data of the task formatted to be stored.
     */
    @Override
    public String formatData() {
        return "D" + " | " + this.formatTask() + " | " + this.by.format(ioFormatter);
    }

    /**
     * Sets the display datetime format to the given pattern.
     *
     * @param pattern The pattern to set the display datetime to.
     */
    public static void setDisplayFormatter(String pattern) {
        Deadline.displayFormatter = DateTimeFormatter.ofPattern(pattern);
    }
}
