package bartenderbob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task that has a description, from date and a due date.
 */
public class Event extends Task {
    //TODO: What happens if by is before from?
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MMM_DD_YYYY = "MMM dd yyyy";
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
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern(YYYY_MM_DD));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(YYYY_MM_DD));
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
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern(YYYY_MM_DD));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(YYYY_MM_DD));
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
            LocalDate.parse(from, DateTimeFormatter.ofPattern(YYYY_MM_DD));
            LocalDate.parse(by, DateTimeFormatter.ofPattern(YYYY_MM_DD));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Verifies whether the event task has no clash with another event task.
     * @param event The event task to be compared with.
     * @return Whether the event task has no clash with another event task.
     */
    public boolean hasNoClash(Event event) {
        return this.by.isBefore(event.from) || event.by.isBefore(this.from);
    }
    /**
     * Shows the event task information when the user uses the 'list' command.
     *
     * @return Complete event task information as a String.
     */
    @Override
    public String show() {
        super.status = isDone ? "X" : " ";
        String fromFormat = this.from.format(DateTimeFormatter.ofPattern(MMM_DD_YYYY));
        String byFormat = this.by.format(DateTimeFormatter.ofPattern(MMM_DD_YYYY));
        String fromByFormat = "(from: " + fromFormat + " to: " + byFormat + ")";
        return "[E]" + "[" + status + "]" + " " + this.description + " " + fromByFormat;
    }
}
