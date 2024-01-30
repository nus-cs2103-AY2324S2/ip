/**
 * Events are tasks that start at a specific date/time
 * and ends at a specific date/time.
 */
public class Event extends Task {
    /**
     * Constructor for an Event object.
     *
     * @param name task name
     * @param from start of event
     * @param to end of event
     */
    public Event(String name, String from, String to) {
        super(name + " (from: " + from + " to: " + to + ")");
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of event
     * @param b isDone
     */
    public Event(String description, boolean b) {
        super(description, b);
    }

    /**
     * toString method for printing task description.
     * @return task type + task status + task name
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
