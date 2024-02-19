package yue.Tasks;

/**
 * Represents an event task loaded from storage.
 */
public class EventTaskLoad extends Task {
    private String time;
    private String from;
    private String to;


    /**
     * Constructs an EventTaskLoad object with the given task description and time.
     *
     * @param task The task description.
     * @param time The time of the event.
     */
    public EventTaskLoad(String task, String time) {
        super(task);
        parseEventLoad(time);
        this.time = time;
    }


    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    public String getTime() {
        return time;
    }


    /**
     * Parses the time string to extract the start and end times of the event.
     *
     * @param time The time string representing the event.
     */
    private void parseEventLoad(String time) {
        String[] split = time.split("-", 2);

            if (split.length == 2) {
                this.from = split[0].trim();
                this.to = split[1].trim();
            }
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (from: " + from + " to: " + to + ")";
    }


    /**
     * Returns the tag for the event task.
     *
     * @return The tag for the event task.
     */
    @Override
    public String tag() {
        return "[E]";
    }
}
