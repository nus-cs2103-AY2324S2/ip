package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the botChat application.
 * Extends the base Task class.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter STANDARD_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    protected String by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     * If the provided deadline is in a valid format, it is used as-is; otherwise, it is converted.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in its original format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = convertDate(by);
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Converts the deadline to a standard format.
     *
     * @param by The deadline to be converted.
     * @return The deadline in a standard format.
     */
    private String convertDate(String by) {
        try {
            LocalDate date = LocalDate.parse(by, INPUT_DATE_FORMATTER);
            return date.format(STANDARD_DATE_FORMATTER);
        } catch (Exception e) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(by, INPUT_DATETIME_FORMATTER);
                return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            } catch (Exception ex) {
                return by;
            }
        }
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
