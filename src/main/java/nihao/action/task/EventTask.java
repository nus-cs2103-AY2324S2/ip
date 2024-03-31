package nihao.action.task;

import java.time.LocalDateTime;

import nihao.handler.DateTimeHandler;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor that specifies the name of the event and both beginning and ending time of the event.
     */
    public EventTask(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of the from LocalDateTime object.
     */
    public String getFromString() {
        return DateTimeHandler.formatOutput(from);
    }

    /**
     * Returns the String representation of the to LocalDateTime object.
     */
    public String getToString() {
        return DateTimeHandler.formatOutput(to);
    }

    /**
     * Returns the String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + getFromString()
                + " to: " + getToString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("invoked eventtask.equals");
        return obj instanceof EventTask && ((EventTask) obj).taskName.equals(taskName)
                && ((EventTask) obj).from.equals(from) && ((EventTask) obj).to.equals(to);
    }
}
