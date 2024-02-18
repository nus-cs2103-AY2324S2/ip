package duke.tasks;

import java.time.LocalDateTime;

/**
 * Class for Event Task
 */
public class Event extends Task {
    protected DateTask from;
    protected DateTask to;

    private final String TASKTYPE = "Event";

    public Event(String description, String from, String to) {
        this(description, from, to , false);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = new DateTask(from);
        this.to = new DateTask(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskType() {
        return TASKTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getDate() {
        return from.getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveFormat() {
        return String.format("%s;;%s;;%s;;%s",
                "E", super.saveFormat(), from.saveFormat(), to.saveFormat());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s%s%s",
                 "[E]", super.toString(), String.format(" (from: %s to: %s)", from, to));
    }
}
