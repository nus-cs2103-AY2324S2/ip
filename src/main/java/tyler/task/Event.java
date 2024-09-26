package tyler.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task. Have two extra argument called start and end to indicate
 * the started and ended date, time of this event.
 */
public class Event extends Task {
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor of Event Task
     *
     * @param name  Name of Task
     * @param start Start time of Task
     * @param end   End time of Task
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor of Event Task
     *
     * @param name   Name of Task
     * @param start  Start Time of Task
     * @param end    End Time of Task
     * @param isDone Status of Task
     */
    public Event(String name, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(name);
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    /**
     * This method is used by storage when the storage need to save file to local.
     *
     * @return String representation of this task to be saved to the local.
     */

    @Override
    public String saveToFileString() {
        return "E | " + super.saveToFileString() + " | " + this.start.format(OUTPUT_DATE_FORMAT)
                + " | " + this.end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[E]" + str + " (from: " + this.start.format(OUTPUT_DATE_FORMAT)
                + " to: " + this.end.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
