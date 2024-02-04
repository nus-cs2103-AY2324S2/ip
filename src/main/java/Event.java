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

    /*
     * Constructs Event object with description, from and to as LocalDate objects.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (from: " + from.format(Task.DATE_TIME_FORMATTER) +
               " to: " + to.format(Task.DATE_TIME_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    public static Event EventFromFileString(String fileString) {
        String[] taskDetails = fileString.split(" \\| ");
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        LocalDate from = LocalDate.parse(taskDetails[3]);
        LocalDate to = LocalDate.parse(taskDetails[4]);
        return new Event(description, from, to, isDone);
    }
}