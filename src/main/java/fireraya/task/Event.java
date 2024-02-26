package fireraya.task;

/**
 * This class holds an Event task.
 *
 * It is extended from the parent class Task.
 */
public class Event extends Task{

    protected String from;
    protected String to;

    /**
     * Constructor for an Event task.
     *
     * @param description description of the task.
     * @param from Date/Time of start of the event
     * @param to Date/Time of end of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides the format to save the file on local device.
     *
     * @return string of the saved format of the task.
     */
    @Override
    public String saveFormat() {
        return String.format("E|%d|%s|%s|%s", isDone ? 1 : 0, description, from, to);
    }

    /**
     * Overrides a string with information about this task.
     *
     * @return string format of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
