package haro.task;

import haro.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a specific deadline in the application, extending the Task class.
 * It provides specific functionality for handling tasks with deadlines.
 */
public class Deadline extends Task {
    String deadline;
    LocalDate deadlineDate;

    /**
     * Constructs a Deadline instance with the specified task description and deadline.
     *
     * @param task     Task description
     * @param deadline Deadline of the task
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;

        try {
            deadlineDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            deadlineDate = null;
        }
    }

    /**
     * Constructs a Deadline instance with the specified task description, deadline, and done status.
     *
     * @param task     Task description
     * @param deadline Deadline of the task
     * @param done     True if the task is marked as done, false otherwise
     */
    public Deadline(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;

        try {
            deadlineDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            deadlineDate = null;
        }
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return Formatted string representation of the deadline task
     */
    @Override
    public String printTask() {
        String deadlineString;
        if (deadlineDate != null) {
            deadlineString = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            deadlineString = this.deadline;
        }
        return "[D]" + super.printTask() + " (by: " + deadlineString + ")";
    }

    /**
     * Returns a string representation of the deadline task for storage purposes.
     * Format: "D | {marked} | {task} | {deadline}"
     *
     * @return String representation of the deadline task for storage
     */
    @Override
    public String toString() {
        int marked = this.done ? 1 : 0;
        return "D | " + marked + " | " + this.task + " | " + this.deadline;
    }
}
