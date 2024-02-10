package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Deadline task
 * It extends the Task class, adding a due date to the task
 */
public class Deadline extends Task {
    // protected String dueDate;
    protected LocalDateTime dueDateTime;

    /**
     * Constructs a new Deadline task with a specified description and due date
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     */
    public Deadline(String description, String dueDate) throws BotException {
        super(description);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            this.dueDateTime = LocalDateTime.parse(dueDate, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                this.dueDateTime = LocalDateTime.parse(dueDate, formatter2);
            } catch (DateTimeParseException e2) {
                throw new BotException(
                        "Invalid date format. Please use either 'd/M/yyyy HHmm' or 'MMM dd yyyy HH:mm'.");
            }
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task type (D for Deadline), the task description, and
     * the due date.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "D" + super.toString() + " | by: " + this.dueDateTime.format(formatter);
    }
}