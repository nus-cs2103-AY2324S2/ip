package action.task;

import handler.DateTimeHandler;

import java.time.LocalDateTime;

public class DeadlineTask extends Task{
    private LocalDateTime by;
    public String getByString() {
        return DateTimeHandler.formatOutput(by);
    }
    public DeadlineTask(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }
}
