package nihao.action.task;

import java.time.LocalDateTime;

import nihao.handler.DateTimeHandler;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;
    /**
     * Constructor that specifies the name and due time of the task.
     */
    public DeadlineTask(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the String representation of the by LocalDateTime object.
     */
    public String getByString() {
        return DateTimeHandler.formatOutput(by);
    }

    /**
     * Returns the String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineTask && ((DeadlineTask) obj).taskName.equals(taskName)
                && ((DeadlineTask) obj).by.equals(by);
    }
}
