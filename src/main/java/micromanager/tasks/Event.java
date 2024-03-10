package micromanager.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents a task with an event duration in the task list.
 * It extends the Task class and provides methods to manage event tasks.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an Event object with the specified task description, start date, and end date.
     *
     * @param taskDescription The description of the event task.
     * @param start           The start date of the event.
     * @param end             The end date of the event.
     */
    public Event(String taskDescription, LocalDate start, LocalDate end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts the event task to a string representation for saving to a file.
     *
     * @return A string representation of the event task for saving to a file.
     */
    @Override
    public String toFileString() {
        return String.format("T,%b,%s,%s,%s", isDone, taskDescription, start, end);
    }

    /**
     * Returns a string representation of the event task.
     * Overrides the toString method in the Task class.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ",
                this.taskDescription,
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }
}
