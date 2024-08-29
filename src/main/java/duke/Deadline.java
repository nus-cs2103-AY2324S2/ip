package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Deadline task
 * It extends the Task class, adding a due date to the task
 */
public class Deadline extends Task {
    protected LocalDateTime dueDateTime;

    /**
     * Constructs a new Deadline task with a specified description and due date
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     * @throws BotException If the due date is in an invalid format.
     */
    public Deadline(String description, String dueDate) throws BotException {
        super(description);
        this.dueDateTime = parseDueDate(dueDate);
    }

    /**
     * Parses the due date using the specified date formats.
     *
     * @param dueDate The due date to parse.
     * @return The parsed due date.
     * @throws BotException If the due date is in an invalid format.
     */
    private LocalDateTime parseDueDate(String dueDate) throws BotException {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dueDate, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new BotException("Invalid date format."
                + "Please use 'MMM dd yyyy HH:mm'."
                + "E.g. 'Sep 30 2029 18:00'");
    }

    /**
     * Returns a string representation of the Deadline task
     * The string includes the task type (D for Deadline), the task description, and
     * the due date
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        assert this.dueDateTime != null : "Due date should not be null";
        return "D" + super.toString() + " | by: " + this.dueDateTime.format(formatter);
    }
}