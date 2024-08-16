package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in a task management application.
 * A deadline task includes a description and a deadline date and time.
 * It extends the Task class by adding a deadline-specific time attribute.
 * The deadline time is managed using the LocalDateTime class.
 * The input time format is d/M/yyyy HHmm (e.g., 2/12/2019 1800).
 * If an invalid date format is provided, an error message is displayed.
 *
 * @author Muhammad Rizki Bayuaji
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline instance with a description and deadline time.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline time as a string in the format "d/M/yyyy HHmm".
     *                    Example: "2/12/2019 1800".
     *                    It is parsed to a LocalDateTime object.
     * @throws DateTimeParseException if the provided deadline time string does not match the expected format.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
        }
    }

    /**
     * Returns the deadline time of the task.
     *
     * @return The deadline time as a LocalDateTime object.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public LocalDateTime getBy(){
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task,
     * including the type indicator, description, and deadline time.
     *
     * @return A string representation of the deadline task.
     *
     * @author Muhammad Rizki Bayuaji
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }
}
