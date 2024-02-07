package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the botChat application.
 * Extends the base Task class.
 */
public class Deadline extends Task {
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
        if (isValidDateFormat(by)) {
            this.by = by;
        } else {
            this.by = convertDate(by);
        }
    }
    public String getBy() {
        return by;
    }

    /**
     * Checks if the provided deadline is in a valid format.
     *
     * @param by The deadline to be checked.
     * @return True if the deadline is in a valid format, false otherwise.
     */
    private boolean isValidDateFormat(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate date = LocalDate.parse(by, formatter);
            String formattedDateTime = date.format(formatter);
            return formattedDateTime.equals(by);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Converts the deadline to a standard format.
     *
     * @param by The deadline to be converted.
     * @return The deadline in a standard format.
     */
    private String convertDate(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(by, inputFormatter);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
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
