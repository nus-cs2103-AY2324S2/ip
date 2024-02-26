package duke.task;

/**
 * Task with a starting time and ending time.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an event object.
     *
     * @param taskName Name of event.
     * @param isDone Completion of event.
     * @param start Start of event.
     * @param end End of event.
     */
    public Event(String taskName, boolean isDone, String start, String end) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "event|" + super.toFileString() + "|" + this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)", start, end);
    }
}
