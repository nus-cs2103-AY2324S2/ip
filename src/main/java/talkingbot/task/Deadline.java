package talkingbot.task;

import talkingbot.type.TaskType;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime endTime;
    public Deadline(String description, boolean isDone, String endTime) {
        super(description, isDone, TaskType.DEADLINE);
        this.endTime = LocalDateTime.parse(endTime, super.dateTimeEntryForm);
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String getSaveFileString() {
        return String.format("D | %d | %s | %s", super.getDoneAsInt(),
                super.getDescription(), this.getEndTime().format(super.dateTimeEntryForm));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                this.getEndTime().format(super.dateTimeOutForm));
    }
}
