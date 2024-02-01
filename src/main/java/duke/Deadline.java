package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String task, String taskType, String deadline) {
        super(task, taskType);
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask() + this.getPeriod();
    }

    public String getPeriod() {
        return " (By: " + dateToString() + ")";
    }

    public String announcement() {
        return "New Deadline created!";
    }

    public String saveString() {
        return this.getTaskTypeSingle() + "|" + this.getStatusBinary() + "|" + this.getTask() + "|"
                + this.dateToString();
    }
    public String dateToString() {
        return this.deadline.format(formatter).replace("T", " ");
    }
}
