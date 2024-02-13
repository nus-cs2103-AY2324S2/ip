package dibo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent an event.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the task.
     * @param start The date the event starts.
     * @param end The date the event ends.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation for the event task,
     * usually for the display format in the ui.
     *
     * @return The string representation of the event task for displaying.
     */
    @Override
    public String toString() {
        String outputStartString = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String outputEndString = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (from: " + outputStartString
                + " to: " + outputEndString + ")";
    }

    /**
     * Returns the string representation of the event task,
     * usually for saving in the text file.
     *
     * @return The string representation of the event task for saving.
     */
    @Override
    public String getSaveFormat() {
        String outputStartString = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String outputEndString = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "event | " + super.getSaveFormat() + " | "
                + outputStartString + " | " + outputEndString;
    }
}
