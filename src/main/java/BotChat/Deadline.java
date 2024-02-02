package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the botChat application.
 * Extends the base Task class.
 */
public class Deadline extends Task {

    /**
     * The deadline string in its original format.
     */
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

    /**
     * Gets the deadline in its original format.
     *
     * @return The deadline in its original format.
     */
    public String getBy() {
        return by;
    }

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

    /**
     * Overrides the toString method to provide a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}