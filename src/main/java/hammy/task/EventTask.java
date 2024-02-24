package hammy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task with a start and end time.
 * It is a subclass of the Task class.
 */
public class EventTask extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructs an EventTask object with the given description, start time, and end time.
     * The task is initialized as not done.
     *
     * @param description the description of the task
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     */
    public EventTask(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs an EventTask object with the given description, start time, end time, and status.
     *
     * @param description the description of the task
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     * @param isDone      the status of the task, true if it is done, false otherwise
     */
    public EventTask(String description, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the EventTask.
     * The string contains the status (done or not done), description, start time, and end time of the event.
     *
     * @return a string representation of the EventTask
     */
    @Override
    public String toString() {
        LocalDate tempStartTime = startTime.plusDays(0);
        LocalDate tempEndTime = endTime.plusDays(0);
        return "[E]" + super.toString() + " (from: " + tempStartTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + tempEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}