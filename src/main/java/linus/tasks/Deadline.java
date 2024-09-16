package linus.tasks;

import java.time.LocalDateTime;

/**
 * Class for Deadline Task
 */
public class Deadline extends Task {
    protected DateTask by;
    protected String TASKTYPE = "Deadline";

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = new DateTask(by);
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
        return by.getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveFormat() {
        return String.format("%s;;%s;;%s",
                "D",super.saveFormat(), by.saveFormat());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s%s%s",
                "[D]", super.toString(), " (by: " + by + ")");
    }
    
}
