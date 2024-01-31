import java.util.Objects;

public class Event extends Task{
    private final String start;
    private final String end;

    /**
     * Constructor for Event class.
     * @param taskName Name of the task.
     * @param start Start time of the event.
     * @param end End time of the event.
     */
    public Event(String taskName, String start, String end, boolean isCompleted) {
        super(taskName, isCompleted);
        this.start = start;
        this.end = end;
    }

    /**
     * Details regarding the event.
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Format of the event to be saved in the file.
     * @return String representation of an event.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s | %s", "E", Objects.equals(super.getStatus(), "X") ? 1 : 0, super.getDesc(), this.start, this.end);
    }
}
