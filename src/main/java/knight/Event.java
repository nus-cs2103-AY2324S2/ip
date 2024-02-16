package knight;

import java.time.LocalDate;

/**
 * Represents a task that starts and ends at a specific time.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;
    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
        assert this.startTime != null : "Time should not be null";
        assert this.endTime != null : "Time should not be null";
    }

    /**
     * Get the command representation of the task.
     *
     * @return The command representation of the task.
     */
    @Override
    String getCommand() {
        return "event " + name + " /from " + startTime + " /to " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(Task.DATE_FORMATTER)
                + " to: " + endTime.format(Task.DATE_FORMATTER) + ")";
    }
}
