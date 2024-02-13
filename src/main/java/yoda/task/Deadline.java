package yoda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a deadline task. It extends the Task class and adds a deadline time.
 */
public class Deadline extends Task {
    private final LocalDateTime BY; // Deadline time for the task

    /**
     * Constructs a Deadline task with a description and a deadline time.
     *
     * @param description A string describing the deadline task.
     * @param by A string representing the time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description); // Calls the constructor of the superclass Task
        this.BY = by;       // Sets the deadline time for this task
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Retrieves the deadline time of the task.
     *
     * @return The deadline time of the task.
     */
    public LocalDateTime getBy() {
        return BY;
    }

    /**
     * Retrieves the deadline time of the task as a string.
     *
     * @return The deadline time of the task as a string.
     */
    public String getByString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return getBy().format(formatter);
    }

    @Override
    public String toFileFormatDetails() {
        return getDescription() + " | " + getByString();
    }


    /**
     * Returns a string representation of the deadline task, including its type,
     * description, and deadline time.
     *
     * @return A formatted string with the type of task, its description, and the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[" + getType() + "]" + super.toString() + " (by: " + BY.format(formatter) + ")";
    }
}
