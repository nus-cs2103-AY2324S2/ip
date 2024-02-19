package lamball.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lamball.exception.InvalidDateException;

/**
 * Event class, an extension of the Task class that has a duration, represented by
 * from and to.
 *
 * @author ongzhili
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event task.
     *
     * @param description Name of task.
     * @param from Start date of event.
     * @param to End date of event.
     * @throws DateTimeParseException if invalid date is provided.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        LocalDate parsedFrom = LocalDate.parse(from);
        LocalDate parsedTo = LocalDate.parse(to);

        if (parsedFrom.isBefore(LocalDate.now()) || parsedTo.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Parsed date is in the paaast.");
        } else if (parsedTo.isBefore(parsedFrom)) {
            throw new InvalidDateException("By should be aaafter from...");
        }
        this.from = parsedFrom;
        this.to = parsedTo;
    }

    @Override
    public String command() {
        return "event " + description + " /from " + from + " /to " + to;
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
