package duke.task;
import java.time.LocalDate;

/**
 * Represents an event task that has both a start and an end date. It extends the {@code Task} class,
 * incorporating the duration of the event.
 */
public class Event extends Task{
    protected LocalDate start;
    protected LocalDate end;
    
    /**
     * Constructs an {@code Event} task with the specified description, start date, and end date.
     *
     * @param description The textual description of the event.
     * @param start The starting date of the event.
     * @param end The ending date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    
    /**
     * Returns the start date of the event.
     *
     * @return The start date as a {@code LocalDate}.
     */
    public LocalDate getStart() {
        return this.start;
    }
    
    /**
     * Returns the end date of the event.
     *
     * @return The end date as a {@code LocalDate}.
     */
    public LocalDate getEnd() {
        return this.end;
    }
    
    /**
     * Returns a string representation of the event task, including its type, status, description, start date, and end date.
     *
     * @return A formatted string indicating the task's type (E for Event), completion status, description, and duration (start to end date).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + formatDateForPrinting(this.getStart())
                + " to " + formatDateForPrinting(this.getEnd()) + ")";
    }
}
