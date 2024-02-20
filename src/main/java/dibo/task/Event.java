package dibo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents an event task of the user.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a new Event object with the specified parameters.
     *
     * @param description The String description of the task.
     * @param start The LocalDate object for the date the event starts.
     * @param end The LocalDate object for the date the event ends.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String outputStartString = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String outputEndString = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (from: " + outputStartString
                + " to: " + outputEndString + ")";
    }

    @Override
    public String getSaveFormat() {
        String outputStartString = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String outputEndString = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "event | " + super.getSaveFormat() + " | "
                + outputStartString + " | " + outputEndString;
    }
}
