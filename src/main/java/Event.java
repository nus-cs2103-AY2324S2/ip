import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/*
 * The Event class is a subclass of Task and represents a task that starts
 * at a specific time and ends at a specific time.
 * It takes in a LocalData as the start time and end time of the event.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /*
     * Constructs Event object with description, from and to as Strings.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(Task.DATE_TIME_FORMATTER) +
               " to: " + to.format(Task.DATE_TIME_FORMATTER) + ")";
    }
}