package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The `Deadline` class represents a task that have a deadline.
 * It provides methods to create strings related to task's details.
 * It extends the `Task` class.
 */
public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalDateTime byDateTime;

    /**
     * Creates a `Deadline` task with description and due date.
     *
     * @param description The task's description.
     * @param byDate The task's due date.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
        this.byDateTime = null;
    }

    /**
     * Creates a `Deadline` task with description, due date and time.
     *
     * @param description The task's description.
     * @param byDateTime The task's due date and time.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDate = null;
        this.byDateTime = byDateTime;
    }

    /**
     * Creates a `Deadline` task with description, status, and due date.
     *
     * @param description The task's description.
     * @param isDone The task's status.
     * @param byDate The task's due date and time.
     */
    public Deadline(String description, boolean isDone, LocalDate byDate) {
        super(description, isDone);
        this.byDate = byDate;
        this.byDateTime = null;
    }

    /**
     * Creates a `Deadline` task with description, status, due date and time.
     *
     * @param description The task's description.
     * @param isDone The task's status.
     * @param byDateTime The task's due date and time.
     */
    public Deadline(String description, boolean isDone, LocalDateTime byDateTime) {
        super(description, isDone);
        this.byDate = null;
        this.byDateTime = byDateTime;
    }

    /**
     * Creates a string representing a number with at least two digits.
     *
     * @param s The input number
     * @return String representing the input number with at least two digits.
     */
    private String padZero(int s) {
        if (s < 10) {
            return "0" + s;
        }
        return Integer.toString(s);
    }

    /**
     * Creates a formatted string to store the due date and time.
     *
     * @return The formatted string to store the due date and time.
     */
    public String getDeadlineForSave() {
        return this.byDate == null
                ? (this.byDateTime.getYear() + "-" + padZero(this.byDateTime.getMonthValue())
                + "-" + padZero(this.byDateTime.getDayOfMonth()) + " " + padZero(this.byDateTime.getHour()) + ":"
                + padZero(this.byDateTime.getMinute()))
                : (this.byDate.getYear() + "-" + padZero(this.byDate.getMonthValue())
                + "-" + padZero(this.byDate.getDayOfMonth()));
    }

    /**
     * Creates a formatted string to display the due date and time.
     *
     * @return The formatted string to display the due date and time.
     */
    public String getDeadlineForDisplay() {
        return this.byDate == null
                ? (this.byDateTime.getMonth() + " " + padZero(this.byDateTime.getDayOfMonth())
                + " " + this.byDateTime.getYear() + " " + padZero((this.byDateTime.getHour() > 12
                ? this.byDateTime.getHour() - 12 : this.byDateTime.getHour())) + ":"
                + padZero(this.byDateTime.getMinute()) + (this.byDateTime.getHour() >= 12 ? "PM" : "AM"))
                : (this.byDate.getMonth() + " " + padZero(this.byDate.getDayOfMonth())
                + " " + this.byDate.getYear());
    }

    /**
     * Returns the formatted string containing done status and description of the task.
     *
     * @return The formatted string containing done status and description of the task.
     */
    @Override
    public String getDescriptionStatus() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by: "
                + this.getDeadlineForDisplay() + ")";
    }

    /**
     * Returns various fields of the task.
     *
     * @return A string array containing the description, done status, and formatted due date and time of a task.
     */
    @Override
    public String[] getFields() {
        String[] result = new String[3];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        result[2] = this.getDeadlineForSave();
        return result;
    }
}
