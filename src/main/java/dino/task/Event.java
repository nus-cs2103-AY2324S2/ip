package dino.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents a Task with a deadline interval. */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs a new Event task with the given description and deadline interval.
     *
     * @param description   The description of the task.
     * @param startTime  The start time of the task.
     * @param endTime The end time of the task.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start time of the task.
     *
     * @return The start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the task.
     *
     * @return The end time.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string representing the task's status, description, and deadline interval.
     */
    public String toString() {
        return String.format(" E | %s | %s | from: %s to: %s",
                getStatusIcon(),
                description,
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
