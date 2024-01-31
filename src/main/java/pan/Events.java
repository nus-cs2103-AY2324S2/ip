package pan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Pan - Represents the Events Class for an Events instance
 * @author Jerome Goh
 */
public class Events extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event instance.
     *
     * @param description The description of the event.
     * @param isDone The status of whether the event has been completed.
     * @param from   The start date time of the event.
     * @param to The end date time of the event.
     */
    public Events(String description, pan.enums.TaskStatus isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the From attribute of the event.
     *
     * @return Java LocalDate instance for the start date time of the event.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Gets the To attribute of the event.
     *
     * @return Java LocalDate instance for the end date time of the event.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Updates the From attribute of the event.
     *
     * @param newFrom Updated From attribute of the event.
     */
    public void setFrom(LocalDate newFrom) {
        this.from = newFrom;
    }

    /**
     * Updates the To attribute of the event.
     *
     * @param newTo Updated To attribute of the event.
     */
    public void setTo(LocalDate newTo) {
        this.to = newTo;
    }

    /**
     * Converts the Event instance into its correpsonding string representation.
     *
     * @return String that represents whether the corresponding Event has been comopleted
     *      as well as its corresponding start and end date time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
