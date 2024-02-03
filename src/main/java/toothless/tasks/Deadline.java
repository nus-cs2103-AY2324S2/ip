package toothless.tasks;

import java.time.LocalDateTime;

/**
 * Represents a deadline task in the Toothless application. A deadline task includes a description,
 * and end date.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructs a Deadline with the specified description and end date.
     * @param description The deadline's description.
     * @param date The end date of the deadline in a parsable datetime format.
     */
    public Deadline(String description, String date) {
        super.description = description;
        this.date = LocalDateTime.parse(date);
    }

    /**
     * Constructs a Deadline with the specified description, end date, and completion status.
     * @param description The deadline's description.
     * @param date The end date of the deadline in a parsable datetime format.
     * @param isDone The deadline's completion status.
     */
    public Deadline(String description, String date, boolean isDone) {
        super.description = description;
        super.isDone = isDone;
        this.date = LocalDateTime.parse(date);
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + date;
    }

    @Override
    public String toString() {
        return description + " (by: "+ super.dateTimeFormat(date) + ")";
    }
}
