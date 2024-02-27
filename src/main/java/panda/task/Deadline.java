package panda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructs a new Deadline with the given description and deadline.
     * 
     * @param desc the description of the task.
     * @param datestr the deadline of the task.
     */
    public Deadline(String desc, String datestr) {
        super(desc);
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("MMM d'th' ha"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd ha"));
        DateTimeFormatter formatter = builder.toFormatter(Locale.US);
        if (!datestr.toLowerCase().contains("am") && !datestr.contains("pm")) {
            datestr += " 12am"; // append default time if none is provided
        }
        this.date = LocalDateTime.parse(datestr, formatter);
    }

    /**
     * Returns the string representation of the deadline.
     * 
     * @return the string representation of the deadline.
     */
    private String dateString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
    }

    /**
     * Returns the string representation of the Deadline.
     * 
     * @return the string representation of the Deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateString() + ")";
    }

    /**
     * Returns the string representation of the Deadline suitable for saving to a file.
     * 
     * @return the string representation of the Deadline.
     */
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + dateString();
    }
}
