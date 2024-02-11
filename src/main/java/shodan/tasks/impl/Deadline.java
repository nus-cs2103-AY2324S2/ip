package shodan.tasks.impl;

import java.time.LocalDateTime;

import shodan.tasks.Task;

/**
 * Represents a task that needs to be completed by a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime endDate;

    /**
     * Instantiates a new Deadline.
     *
     * @param name    the name of the task.
     * @param endDate the deadline of the task.
     */
    public Deadline(String name, LocalDateTime endDate) {
        super(name);
        this.endDate = endDate;
    }

    /**
     * Gets the deadline of the task as a string.
     *
     * @return the end date.
     */
    public String getEndDate() {
        return endDate.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endDate.format(Task.DTF));
    }
}
