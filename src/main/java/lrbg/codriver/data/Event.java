package lrbg.codriver.data;

import java.time.LocalDate;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /** The start date of the event. */
    private final LocalDate startDate;
    /** The end date of the event. */
    private final LocalDate endDate;

    /**
     * Constructor for Event.
     * @param description The description of the task.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate
                + " to: " + this.endDate + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileSaveString() {
        return "E|" + super.toFileSaveString() + "|" + this.startDate + "~" + this.endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            Event other = (Event) obj;
            return other.getDescription().equals(this.getDescription())
                    && other.startDate.equals(this.startDate)
                    && other.endDate.equals(this.endDate);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getDescription().hashCode() + this.startDate.hashCode() + this.endDate.hashCode();
    }
}