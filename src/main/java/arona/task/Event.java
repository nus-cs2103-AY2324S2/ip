package arona.task;

import arona.exception.AronaInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a "Event" task,
 * which is a task with a start time and end time attached.
 *
 * @author Maximilliano Utomo
 */
public class Event extends Task {
    /**
     * The start date of the task.
     */
    LocalDate startDate;
    /**
     * The end date of the task.
     */
    LocalDate endDate;

    /**
     * A public constructor for the task.Event.
     * @param desc - the description of the task
     * @param startDate - the start time of the task
     * @param endDate - the end time of the task
     */
    public Event(String desc, String startDate, String endDate) throws AronaInvalidDateException {
        super(desc);
        try {
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            throw new AronaInvalidDateException("");
        }
    }

    /**
     * Represent the task into a String format applicable for saving data.
     * Uses an extra E| to represent a task.Event.
     * @return A data representation of the task.Event
     */
    @Override
    public String toDataFormat() {
        return "E|" + super.toDataFormat() + "|" + this.startDate + "|" + this.endDate;
    }

    /**
     * Represent the task into a String format applicable for printing output.
     * Uses an extra [E] to represent a task.Event.
     * @return A String representation of the task.Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
