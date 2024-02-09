package echon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Creates a deadline task.
     *
     * @param description Description of the task.
     * @param dueDate Due date of the task.
     * @throws EchonException If the due date is not in the correct format.
     */
    public Deadline(String description, String dueDate) throws EchonException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new EchonException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D | %s | %s", fileLine.substring(4), this.dueDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate.format(formatter));
    }
}
