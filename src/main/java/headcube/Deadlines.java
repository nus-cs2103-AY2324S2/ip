package headcube;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline. A Deadlines object
 * includes a due date along with the task description.
 */
public class Deadlines extends Task {
    protected LocalDate by;
    private static final DateTimeFormatter TARGET_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter ALTERNATIVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor of a Deadlines task with the specified description and deadline.
     * Tries to parse the deadline using either "MMM dd yyyy" or "yyyy-MM-dd" format.
     * If parsing fails, sets the deadline to the current date.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date in "MMM dd yyyy" or "yyyy-MM-dd" format.
     */
    public Deadlines(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by, TARGET_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.by = LocalDate.parse(by, ALTERNATIVE_FORMATTER);
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date format. Using current date");
                this.by = LocalDate.now();
            }

        }
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, status, description, and deadline.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(TARGET_FORMATTER) + ")";
    }

    /**
     * Returns the file format representation of the deadline task,
     * useful for saving the task to a file.
     *
     * @return A string formatted for file saving.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | "
                + "(by: " + by.format(TARGET_FORMATTER) + ")";
    }
}

