package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Extends the Task class.
 */
public class Deadline extends Task {
    private LocalDateTime by;


    /**
     * Constructs a new Deadline instance.
     *
     * @param taskDescription The description of the deadline task.
     * @param isDone          Indicates whether the deadline task has been completed.
     * @param by              When the task should be done by.
     */
    public Deadline(String taskDescription, Boolean isDone, LocalDateTime by) {
        super(taskDescription, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string containing task type, description and by when it should be done.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy | HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline object to save to file.
     *
     * @return A string containing task type, description, when to finish by formatted for file saving.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D| " + super.toFileString() + "|" + this.by.format(formatter);
    }
}