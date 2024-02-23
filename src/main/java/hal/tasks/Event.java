package hal.tasks;
import java.time.LocalDateTime;

import hal.codec.TimeProcessor;

/**
 * The `Event` class represents a task with a specific event duration.
 * It extends the `Task` class and inherits its properties and methods.
 * It has to be serializable so that it can be cloned easily.
 */
public class Event extends Task {
    private static final long serialVersionUID = 3L;
    private final LocalDateTime from;
    private final LocalDateTime to;


    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        this(taskName, from, to, false);
    }

    /**
     * Constructs a new `Event` task with the given task name, event start time, end time, and completion status.
     *
     * @param taskName The name or description of the `Event` task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param done A boolean indicating whether the task is completed (true) or not (false).
     */
    public Event(String taskName, LocalDateTime from, LocalDateTime to, Boolean done) {
        super(taskName, done);
        super.identifier = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s from: %s to: %s",
                super.toString(),
                TimeProcessor.toString(from),
                TimeProcessor.toString(to)
        );
    }

    @Override
    public String[] encode() {
        String[] encodedEvent = new String[5];
        String[] encodedTask = super.encode();

        System.arraycopy(encodedTask, 0, encodedEvent, 0, encodedTask.length);

        encodedEvent[3] = TimeProcessor.toString(from);
        encodedEvent[4] = TimeProcessor.toString(to);

        return encodedEvent;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }

    public LocalDateTime getFrom() {
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }
}
