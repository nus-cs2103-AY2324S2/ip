package bartenderbob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task that has a description, from date and a due date.
 */
public class Event extends Task {
    /** Event start date */
    private LocalDate from;
    /** Event due date */
    private LocalDate by;

    /**
     * Creates an instance of an Event class that has a description, from date
     * and a by date.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param by Due date of the event.
     */
    public Event(String description, String from, String by) {
        super(description);
        if (!isValidDateFormat(from, by)) {
            throw new IllegalArgumentException();
        }
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Creates an instance of an Event class that has a description, from date,
     * by date and whether it has been completed.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param by Due date of the event.
     * @param isDone Represents whether the task has been completed.
     */
    public Event(String description, String from, String by, boolean isDone) {
        super(description, isDone);
        if (!isValidDateFormat(from, by)) {
            throw new IllegalArgumentException();
        }
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Verifies whether a string is of the format yyyy-MM-dd.
     *
     * @param by Input String.
     * @return Whether the string follows the format yyyy-MM-dd.
     */
    private boolean isValidDateFormat(String from, String by) {
        assert from != null : "String parameter 'from' cannot be null";
        assert by != null : "String parameter 'by' cannot be null";
        try {
            LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Shows the event task information when the user uses the 'list' command.
     *
     * @return Complete event task information as a String.
     */
    @Override
    public String show() {
        super.status = isDone ? "X" : " ";
        String fromFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String byFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String fromByFormat = "(from: " + fromFormat + " to: " + byFormat + ")";
        return "[E]" + "[" + status + "]" + " " + this.description + " " + fromByFormat;
    }
}
