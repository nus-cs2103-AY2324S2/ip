package duchess.task;

/**
 * Event class represents an event task with a start and end time or date in the Duchess program.
 * It currently supports a String as a start and end.
 * It extends the Task class and provides methods to manipulate Event tasks.
 */
public class Event extends Task {
    protected String start; //Date or time, but just generic for now
    protected String end;

    /**
     * Constructs an Event object with the given description, start, and end.
     *
     * @param description the description of the event task
     * @param start the start time or date of the event
     * @param end the end time or date of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event object with the given description, completion status, start, and end.
     *
     * @param description the description of the event task
     * @param isDone true if the task is completed, false otherwise
     * @param start the start time or date of the event
     * @param end the end time or date of the event
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return a string representing the Event task including its type, completion status, description, start, and end
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string representation of the Event task in file format.
     *
     * @return a string representing the Event task including its type, completion status, description,
     * start, and end for file storage
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
