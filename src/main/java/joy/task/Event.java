package joy.task;

/**
 * Represents an Event task in the task list.
 * An Event task is a task with a start and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event task with the given start and end time.
     *
     * @param description The description of the Event task.
     * @param start The start time of the Event task.
     * @param end The end time of the Event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns start and end times of the Event task.
     *
     * @return start and end times of the Event task.
     */
    public String getAt() {
        return this.start + " from " + this.end;
    }



    /**
     * Returns a string representation of the Event task for saving to a file.
     *
     * @return A formatted string representing the Event task for saving to a file.
     */
    @Override
    public String toFileString() {
        // Format: E | 0/1 | description | at from to
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + getAt();
    }

    /**
     * @param t The task to be checked
     * @return if the task is a duplicate
     */
    @Override
    public boolean equals(Task t) {
        if (!(t instanceof Event)) {
            return false;
        }

        return t.toString().equals(this.toString());
    }
}
