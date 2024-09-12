package task;

import andelu.PriorityLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * A class to create Deadline Object.
 * A subclass of Task.
 */
public class Deadline extends Task {

    /** The DateTime to complete this task. */
    private LocalDateTime by;

    /**
     * A constructor to create the Deadline Object.
     *
     * @param description The title of the task.
     * @param isDone The status of the task.
     * @param by The DateTime to complete the Deadline.
     */
    public Deadline(String description, boolean isDone, PriorityLevel priorityLevel, LocalDateTime by) {
        super(description, isDone, priorityLevel);
        this.by = by;
    }

    /**
     * Returns the DateTime of the deadline.
     *
     * @return 'by'.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Prints the information of the Deadline Object.
     *
     * @return the information of the Deadline.
     */
    @Override
    public String toString() {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );

        String byString = "";
        for (DateTimeFormatter formatter : formatters) {
            try {
                byString = this.by.format(formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        return "[D][" + super.getStatusIcon() + "] "
                + super.getDescription()
                + " (by: " + byString + ")"
                + " (" + super.getPriorityLevel() + " priority)";
    }
}
