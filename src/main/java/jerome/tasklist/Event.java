package jerome.tasklist;

import jerome.common.DateTimeHandler;
import jerome.exception.MalformedUserInputException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private DateTimeHandler startTime;
    private DateTimeHandler endTime;

    /**
     * Initializes a new Event task with the given description, start time, end time, and completion status.
     *
     * @param description the description of the Event task.
     * @param startTime the start time of the Event task in the format "yyyy-MM-dd".
     * @param endTime the end time of the Event task in the format "yyyy-MM-dd".
     * @param isDone the completion status of the Event task.
     * @throws MalformedUserInputException if the user input is dirty.
     */
    public Event(String description, String startTime, String endTime, boolean isDone)
            throws MalformedUserInputException {
        super(description, isDone);
        this.startTime = new DateTimeHandler(startTime);
        this.endTime = new DateTimeHandler(endTime);
    }

    /**
     * Initializes a new Event task with the given description, start time, end time, priority and completion status.
     *
     * @param description the description of the Event task.
     * @param startTime the start time of the Event task in the format "yyyy-MM-dd".
     * @param endTime the end time of the Event task in the format "yyyy-MM-dd".
     * @param isDone the completion status of the Event task.
     * @param priority    the priority level of the task.
     * @throws MalformedUserInputException if the user input is dirty.
     */
    public Event(String description, String startTime, String endTime, boolean isDone, Priority priority)
            throws MalformedUserInputException {
        super(description, isDone);
        this.startTime = new DateTimeHandler(startTime);
        this.endTime = new DateTimeHandler(endTime);
        super.setPriority(priority);
    }

    /**
     * Returns a string representation of the Event object.
     * @return a string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Returns a string representation of the Event object in a format suitable for storage.
     * @return a string representation of the Event object in a format suitable for storage
     */
    @Override
    public String toStorageString() {
        return "E | " + this.getDescription() + " | " + super.getStatus() + " | "
                + this.startTime.toStorageString() + " | " + this.endTime.toStorageString()
                + " | " + this.getPriority();
    }


}
