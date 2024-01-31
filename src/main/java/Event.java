/**
 * Events are tasks that start at a specific date/time
 * and ends at a specific date/time.
 */
public class Event extends Task {
    // Task description consist of name, start and end of task.
    protected String[] description = new String[3];

    /**
     * Constructor for an Event object.
     *
     * @param name task name
     * @param from start of event
     * @param to end of event
     */
    public Event(String name, String from, String to) {
        super();
        this.description[0] = name;
        this.description[1] = from;
        this.description[2] = to;
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of event
     * @param b isDone
     */
    public Event(String description, boolean b) {
        super(b);
        int fromIdx = description.indexOf("/from");
        int toIdx = description.indexOf("/to");
        this.description[0] = description.substring(6, fromIdx - 1);
        this.description[1] = description.substring(fromIdx + 6, toIdx - 1);
        this.description[2] = description.substring(toIdx + 4);
    }

    /**
     * toString method for printing task description.
     * @return task status + task type + task name + duration
     */
    @Override
    public String toString() {
        return super.toString() + "[E] " + this.description[0]
                + "(from: " + this.description[1] + " to: " + this.description[2] + ")";
    }
}
