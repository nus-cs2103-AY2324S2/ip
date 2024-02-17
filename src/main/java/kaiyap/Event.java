package kaiyap;

import java.time.LocalDateTime;

/**
 * Represents an event task in the KaiYap application.
 * An event task is characterized by a start time and an end time.
 * This class extends the Task class, inheriting its basic properties,
 * while adding specific time details relevant to an event.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    /**
     * Constructs a new Event task with the specified details.
     *
     * @param listItem  The string representation of the task to be displayed in the list.
     * @param inputItem The original input string used to create the task.
     * @param startTime The start time of the event, represented as a LocalDateTime object.
     * @param endTime   The end time of the event, represented as a LocalDateTime object.
     */
    public Event(String listItem, String inputItem, LocalDateTime startTime, LocalDateTime endTime) {
        super(listItem, inputItem);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]"
                + (this.isCompleted ? "[X] " : "[ ] ")
                + this.listItem
                + " (from: "
                + this.dtf.format(this.startTime)
                + " to: "
                + this.dtf.format(this.endTime)
                + ")";
    }
}
