package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@inheritDoc}
 *
 * In this subclass we have the start date and the end date of the Event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs the class Event.
     *
     * @param description The description of the Event task.
     * @param from The start date of the Event task.
     * @param to The end date of the Event task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fileString() {
        return "E/" + super.fileString() + "/" + this.from + "/" + this.to;
    }
}

