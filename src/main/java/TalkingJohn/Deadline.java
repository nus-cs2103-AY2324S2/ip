package talkingjohn;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a due date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time of the deadline task in the format "by: MMM dd yyyy HH:mm" or "by: dd/MM/yyyy HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != null : "description cannot be null";
        assert by != null : "by cannot be null";
        String time = by.substring(3);
        this.by = parseDateTime(time);
    }

    /**
     * Parses the due date/time string into a LocalDateTime object.
     *
     * @param by The due date and time string to be parsed.
     * @return The LocalDateTime object representing the due date and time.
     */
    private LocalDateTime parseDateTime(String by) {
        assert by != null : "by cannot be null";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            DateTimeFormatter alternativeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(by, alternativeFormatter);
        }
    }

    /**
     * Converts the LocalDateTime object to a formatted string representation.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted string representation of the LocalDateTime object.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        assert dateTime != null : "dateTime cannot be null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object, including its description and due date/time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatDateTime(by) + ")";
    }
}