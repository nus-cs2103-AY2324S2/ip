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
        String taskTypeStr = "[D] ";
        String byDateTimeStr = " (by: "
                + this.deadlineDateTime.formatDateTime()
                + ")";

        return taskTypeStr
                + super.toString()
                + byDateTimeStr;
    }

    @Override
    public Task copy() {
        Deadline deadlineCopy = new Deadline(
                super.description,
                this.deadlineDateTime
        );
        deadlineCopy.isDone = this.isDone;

        return deadlineCopy;
    }

    @Override
    public String serializeTask() {
        String taskTypeSerializedStr = "D | ";
        String deadlineDateTimeSerializedStr = " | "
                + this.deadlineDateTime.serializeDateTime();

        return taskTypeSerializedStr
                + super.serializeTask()
                + deadlineDateTimeSerializedStr;
    }
}