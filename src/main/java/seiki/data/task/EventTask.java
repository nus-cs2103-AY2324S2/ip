package seiki.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an EventTask.
 */
public class EventTask extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructor of the EventTask.
     * @param taskName      the title of the task.
     * @param startDateTime the start date & time of the task.
     * @param endDateTime   the end date & time of the task.
     */
    public EventTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructor of the EventTask with status set.
     * @param taskName      the title of the task.
     * @param startDateTime the start date & time of the task.
     * @param endDateTime   the end date & time of the task.
     * @param isDone        the status of the task.
     */
    public EventTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone) {
        super(taskName, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.taskTitle + " (from: "
                + this.startDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + " to: "
                + this.endDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + ")"
                + super.getStatusIcon();
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + " | "
                + this.startDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) + "-"
                + this.endDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }
}
