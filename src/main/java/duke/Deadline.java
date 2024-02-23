package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Deadline task
 */
public class Deadline extends Task{
    protected String by = "";
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts date to type String
     * @return String task format
     */
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.printWithStatus() + " (by: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.printWithStatus() + " (by: " +
                    by + ")";
        }

    }
}
