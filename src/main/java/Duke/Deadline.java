package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Deadline task.
 * It extends the Task class with a specific due date and a specific string representation.
 */
public class Deadline extends Task {
    private LocalDate date;
    static final DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a new Deadline task with the specified name and due date.
     *
     * @param name the name of the Deadline task
     * @param date the due date of the task
     * @throws InvalidDateFormat if the date format is invalid
     */
    public Deadline(String name, String date) throws InvalidDateFormat {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The returned string includes the task type ([D]), the string representation of the superclass, and the due date.
     *
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        String date = String.format(" (by: %s)", this.date.format(f));
        return "[D]" + super.toString() + date;
    }
}