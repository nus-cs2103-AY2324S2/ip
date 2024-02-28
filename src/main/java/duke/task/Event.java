package duke.task;

/**
 * The Event class represents a task with a specific event in the Duke application.
 * It extends the Task class and includes additional functionality to handle events.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isComplete  The completion status of the task.
     */
    public Event(String description, String from, String to, boolean isComplete) {
        super(description);
        this.from = from;
        this.to = to;
        this.isComplete = isComplete;
    }

    /**
     * Converts the Event object to a format suitable for saving to a file.
     *
     * @return The Event object formatted as a string for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s to %s", isComplete ? 1 : 0, description, from, to);
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string containing the task type, completion status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "E | " + (isComplete ? 1 : 0) + " | " + description + " | " + from + " to " + to;
    }
}
