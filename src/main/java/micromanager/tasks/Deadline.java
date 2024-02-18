package micromanager.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a task with a deadline in the task list.
 * It extends the Task class and provides methods to manage deadline tasks.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructs a Deadline object with the specified task description and deadline.
     *
     * @param taskDescription The description of the deadline task.
     * @param deadline        The deadline of the deadline task.
     */
    public Deadline(String taskDescription, LocalDate deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    /**
     * Converts the deadline task to a string representation for saving to a file.
     *
     * @return A string representation of the deadline task for saving to a file.
     */
    @Override
    public String toFileString() {
        return String.format("T,%b,%s,%s", isDone, taskDescription, deadline);
    }

    /**
     * Returns a string representation of the deadline task.
     * Overrides the toString method in the Task class.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ",
                this.taskDescription,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
