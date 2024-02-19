package bartenderbob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Event task that has a description, from date and a due date.
 */
public class Event extends Task {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final DateTimeFormatter YYYY_MM_DD_FORMAT = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    public static final String MMM_DD_YYYY = "MMM dd yyyy";
    public static final DateTimeFormatter MMM_DD_YYYY_FORMAT = DateTimeFormatter.ofPattern(MMM_DD_YYYY);
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
        verifyValidDateFormat(from, by);
        verifyFromBeforeBy(from, by);
        this.from = LocalDate.parse(from, YYYY_MM_DD_FORMAT);
        this.by = LocalDate.parse(by, YYYY_MM_DD_FORMAT);
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
        verifyValidDateFormat(from, by);
        verifyFromBeforeBy(from, by);
        this.from = LocalDate.parse(from, YYYY_MM_DD_FORMAT);
        this.by = LocalDate.parse(by, YYYY_MM_DD_FORMAT);
    }
    /**
     * Verifies whether a string is of the format yyyy-MM-dd.
     *
     * @param by Input String.
     * @return Whether the string follows the format yyyy-MM-dd.
     */
    private void verifyValidDateFormat(String from, String by) {
        assert from != null : "String parameter 'from' cannot be null";
        assert by != null : "String parameter 'by' cannot be null";
        try {
            LocalDate.parse(from, YYYY_MM_DD_FORMAT);
            LocalDate.parse(by, YYYY_MM_DD_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Verifies whether the from date is before the by date.
     *
     * @param from Start date of the event.
     * @param by Due date of the event.
     */
    private void verifyFromBeforeBy(String from, String by) {
        LocalDate fromDate = LocalDate.parse(from, YYYY_MM_DD_FORMAT);
        LocalDate byDate = LocalDate.parse(by, YYYY_MM_DD_FORMAT);
        if (!fromDate.isBefore(byDate)) {
            throw new IllegalArgumentException();
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
        String fromFormat = this.from.format(MMM_DD_YYYY_FORMAT);
        String byFormat = this.by.format(MMM_DD_YYYY_FORMAT);
        String fromByFormat = "(from: " + fromFormat + " to: " + byFormat + ")";
        return "[E]" + "[" + status + "]" + " " + this.description + " " + fromByFormat;
    }
}
