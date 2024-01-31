package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initializes a Deadline task with the description and date.
     *
     * @param description the description of the task
     * @param by the date of the task in LocalDate object
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type of the task.
     *
     * @return task type
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the date of the task in LocalDate object.
     *
     * @return date of the task
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns all the information of the task in String format.
     *
     * @return date of the task in String format
     */
    @Override
    public String toString() {
        return "[D]" +  super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}