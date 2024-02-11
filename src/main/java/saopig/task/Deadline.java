package saopig.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime by;
    private ZoneId userZoneId = ZoneId.systemDefault();
    private ZonedDateTime byZonedDateTime;

    /**
     * Creates a Deadline task with the given description and deadline.
     * The deadline is stored as a LocalDateTime object.
     * The deadline is also stored as a ZonedDateTime object, which is used to print the deadline in the user's.
     * The user's timezone is obtained from the system.
     *
     * @param description Description of the Deadline task.
     * @param by Deadline of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.byZonedDateTime = by.atZone(userZoneId);
    }

    /**
     * Returns the deadline of the Deadline task as LocalDateTime object.
     *
     * @return Deadline of the Deadline task as LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return this.by;
    }
    /**
     * Changes the deadline of the Deadline task to the new deadline.
     *
     * @param newBy New deadline of the Deadline task.
     */
    public void changeDeadline(LocalDateTime newBy) {
        this.by = newBy;
        this.byZonedDateTime = newBy.atZone(userZoneId);
    }
    /**
     * Returns the type of the task.
     *
     * @return "D" representing the Deadline task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns a string which displays the Deadline task in FULL format.
     *
     * @return Deadline of the Deadline task as ZonedDateTime object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + formatter.format(byZonedDateTime) + ")";
    }
}
