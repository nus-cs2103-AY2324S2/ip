package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with start and end date-time.
 * Extends the abstract base class Task.
 */
public class Event extends Task implements RecurringTask {
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean isRecurring;

    /**
     * Initializes a new Event task with the given description, start, and end date-time.
     *
     * @param description The description of the event task.
     * @param start       The start date and time of the event.
     * @param end         The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        this.isRecurring = false;
    }

    /**
     * Returns a string representation of the Event task,
     * including its status icon, description, start, and end date-time.
     *
     * @return A string representing the Event task with its start and end date-time.
     */
    @Override
    public String toString() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String startDateTime = this.start.format(customFormatter);
        String endDateTime = this.end.format(customFormatter);
        return "[E]" + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    /**
     * Returns a string representation of the Event task for saving,
     * including task type, completion status, description, start, and end date-time.
     *
     * @return A string representing the Event task for saving in text file.
     */
    @Override
    public String toSave() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String startDateTime = this.start.format(customFormatter);
        String endDateTime = this.end.format(customFormatter);
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description
                + " | " + startDateTime + " | " + endDateTime;
    }

    @Override
    public String makeRecur() {
        this.isRecurring = true;
        return this.toString();
    }

    @Override
    public LocalDateTime getEndDateTime() {
        return this.end;
    }

    /**
     * Checks if the task is recurring.
     *
     * @return True if the task is recurring, false otherwise.
     */
    @Override
    public boolean isRecurring() {
        return this.isRecurring;
    }

    /**
     * Adjusts the deadline for a recurring task.
     */
    @Override
    public void adjustDeadline() {
        this.start = this.start.plusWeeks(1);
        this.end = this.end.plusWeeks(1);
    }
}
