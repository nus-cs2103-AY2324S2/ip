package lia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructs a Deadline object with the given description, date string, and completion status.
     *
     * @param description The description of the deadline task.
     * @param dateString  The date string representing the deadline.
     * @param isDone      The completion status of the task.
     * @param isImp        The importance of the task.
     */
    public Deadline(String description, String dateString, boolean isDone, boolean isImp) {
        super(description, isDone, isImp);
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Overrides the toString method to provide a custom string representation of the Deadline object.
     *
     * @return A formatted string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "]" + "[" + getStatusIcon() + "]" + "[" + getImpIcon() + "] " + getDescription()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
