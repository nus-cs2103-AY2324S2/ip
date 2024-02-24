package hal.tasks;
import java.time.LocalDateTime;

import hal.codec.TimeProcessor;

/**
 * The `Deadline` class represents a task with a specific deadline.
 * It extends the `Task` class and inherits its properties and methods.
 * It has to be serializable so that it can be cloned easily.
 */
public class Deadline extends Task {
    private static final long serialVersionUID = 4L;
    private final LocalDateTime by;


    public Deadline(String taskName, LocalDateTime by) {
        this(taskName, by, false);
    }

    /**
     * Constructs a new `Deadline` task with the given task name, deadline, and completion status.
     *
     * @param taskName The name or description of the `Deadline` task.
     * @param by The deadline for the task.
     * @param done A boolean indicating whether the task is completed (true) or not (false).
     */
    public Deadline(String taskName, LocalDateTime by, Boolean done) {
        super(taskName, done);
        super.identifier = "D";
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s by: %s", super.toString(), TimeProcessor.toString(by));
    }


    @Override
    public String[] encode() {
        String[] encodedDeadline = new String[4];
        String[] encodedTask = super.encode();

        System.arraycopy(encodedTask, 0, encodedDeadline, 0, encodedTask.length);

        encodedDeadline[3] = TimeProcessor.toString(by);

        return encodedDeadline;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }

    public LocalDateTime getBy() {
        return by;
    }
}
