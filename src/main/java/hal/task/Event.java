package hal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specified period of time.
 */
public class Event extends Task {

    protected LocalDate deadlineFrom;
    protected LocalDate deadlineTo;
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String DIVIDER = " | ";

    /**
     * Constructs a new Event object.
     *
     * @param isDone      Indicates if the task is done.
     * @param description The description of the task.
     * @param deadlineFrom The starting date of the event.
     * @param deadlineTo  The ending date of the event.
     */
    public Event(boolean isDone, String description, String deadlineFrom, String deadlineTo) {
        this.isDone = isDone;
        this.description = description;
        this.deadlineFrom = LocalDate.parse(deadlineFrom);
        this.deadlineTo = LocalDate.parse(deadlineTo);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " +
                deadlineFrom.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                + " to: " + deadlineTo.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    /**
     * Returns a string representing the task suitable for saving to a file.
     *
     * @return A string representing the task for file storage.
     */
    @Override
    public String getFileString() {
        return "E" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description
                + DIVIDER + deadlineFrom + DIVIDER + deadlineTo;
    }

}
