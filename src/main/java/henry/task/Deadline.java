package henry.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import henry.HenryException;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime date;
    /**
     * Creates a Deadline object.
     *
     * @param description The description of the deadline.
     * @param date The due date of the deadline.
     * @throws HenryException If the description or due date is not specified.
     */
    public Deadline(String description, String date) throws HenryException {
        super(description);

        if (date.isBlank()) {
            throw new HenryException("No due date specified!");
        }

        this.date = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    /**
     * Sets the due datetime of the deadline.
     * @param date The due date of the deadline.
     * @throws HenryException If the due date is not specified.
     */
    public void setBy(String date) throws HenryException {
        if (date.isBlank()) {
            throw new HenryException("No due date specified!");
        }
        this.date = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), date.format(INPUT_FORMATTER));
    }
}
