package jmsandiegoo.tyrone.task;

import jmsandiegoo.tyrone.common.DateTime;

/**
 * Represents the deadline task of the application.
 */
public class Deadline extends Task {
    private final DateTime deadlineDateTime;

    /**
     * @param description - the description of the task.
     * @param deadlineDateTime - the deadline datetime of the task.
     * */
    public Deadline(String description, DateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + " (by: "
                + this.deadlineDateTime.formatDateTime()
                + ")";
    }

    @Override
    public String serializeTask() {
        return "D | "
                + super.serializeTask()
                + " | "
                + this.deadlineDateTime.serializeDateTime();
    }
}