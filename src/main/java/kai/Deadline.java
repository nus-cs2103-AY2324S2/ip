package kai;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Extends the base Task class and includes additional information about the deadline.
 */

public class Deadline extends Task implements Serializable {

    /**
     * The deadline date and time
     */
    protected LocalDateTime by;

    /**
     * The day of the week if the deadline is specified as a day (e.g., Monday).
     */
    protected DayOfWeek dayOfWeek;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in string format.
     */
    public Deadline(String description, String by) {
        super(description);
        parseDeadline(by.trim());
    }

    /**
     * Parses the deadline string to determine the deadline date and time or day of the week.
     *
     * @param by The deadline in string format.
     */
    private void parseDeadline(String by) {
        try {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.by = LocalDateTime.parse(by, dateTimeFormatter);

        } catch (DateTimeParseException e) {

            try {
                // If parsing as specific date/time format fails, try parsing as day of the week
                DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE");
                this.dayOfWeek = DayOfWeek.from(dayOfWeekFormatter.parse(by));

            } catch (DateTimeParseException error) {
                System.err.println("Error: Invalid input. Please provide a valid period of the week again.");
            }
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {

        if (by != null) {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + dayOfWeek + ")";
        }
    }
}
