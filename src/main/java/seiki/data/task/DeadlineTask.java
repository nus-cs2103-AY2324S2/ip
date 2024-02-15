package seiki.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a DeadlineTask.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor of the DeadlineTask.
     * @param taskName  the title of the task.
     * @param dateTime  the date & time of the task.
     */
    public DeadlineTask(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    /**
     * Constructor of the DeadlineTask with status set.
     * @param taskName  the title of the task.
     * @param dateTime  the date & time of the task.
     * @param isDone    the status of the task.
     */
    public DeadlineTask(String taskName, LocalDateTime dateTime, boolean isDone) {
        super(taskName, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.taskTitle + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                + ")" + super.getStatusIcon();
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + " | "
                + dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }
}
