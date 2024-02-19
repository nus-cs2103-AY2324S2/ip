package luke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDate;
    protected boolean hasByDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy HHmm");

    protected DateTimeFormatter dateTimeStringFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu hh:mma");

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline of the task, in the format "M/dd/yyyy HHmm" (e.g., "2/12/2022 1800")
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.byDate = LocalDateTime.parse(by, dateTimeFormatter);
            this.hasByDate = true;
        } catch (DateTimeParseException e) {
            this.by = by;
            this.hasByDate = false;
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        if (hasByDate) {
            return "[D]" + super.toString() + " (by: " + dateTimeStringFormatter.format(byDate) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    @Override
    protected String queryType() {
        return "Deadline";
    }

    @Override
    protected void changeBy(String newString) {
        this.by = newString;
    }
}
