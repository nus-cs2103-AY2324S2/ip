package task;

/**
 * Events are tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String name;
    protected String from;
    protected String to;


    /**
     * Constructor for an Event object.
     *
     * @param description Includes name, event start and event end.
     */
    public Event(String[] description) {
        super();
        this.name = description[0];
        this.from = description[1];
        this.to = description[2];
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of the event.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, boolean isDone) {
        super(isDone);
        int fromIdx = description.indexOf("(from:");
        int toIdx = description.indexOf("to:");
        int len = description.length();

        this.name = description.substring(0, fromIdx - 1);
        this.from = description.substring(fromIdx + 7, toIdx - 1);
        this.to = description.substring(toIdx + 4, len - 1);
    }

    @Override
    public boolean isMatchKeyword(String keyword) {
        return this.name.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + "[E] " + this.name
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
