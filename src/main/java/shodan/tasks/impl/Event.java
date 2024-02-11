package shodan.tasks.impl;

import java.time.LocalDateTime;

import shodan.tasks.Task;

/**
 * Represents a task that runs from a start to an end date.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Instantiates a new Event.
     *
     * @param name      the name of the task.
     * @param startDate the start date
     * @param endDate   the end date
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the end date of the task as a string.
     *
     * @return a string representing the end date.
     */
    public String getEndDate() {
        return endDate.toString();
    }

    /**
     * Gets the start date of the task as a string.
     *
     * @return a string representing the end date.
     */
    public String getStartDate() {
        return startDate.toString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startDate.format(shodan.tasks.Task.DTF),
                endDate.format(shodan.tasks.Task.DTF));
    }
}
