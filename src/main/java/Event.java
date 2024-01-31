/**
 * This class represents a Task with a start and end duration
 */
public class Event extends Task {

    private static final String TYPE_SYMBOL = "[E]";
    private final String from;
    private final String to;

    /**
     * Constructs a new Event with the specified description, start, and end period.
     *
     * @param description Description of the Event
     * @param from Starting period of the Event
     * @param to Ending period of the Event
     * @throws MeanDukeException if starting or ending period is invalid
     */
    public Event(String description, String from, String to) throws MeanDukeException {
        super(description, TYPE_SYMBOL);
        if (from.isEmpty() || to.isEmpty()) {
            throw new MeanDukeException("Usage: \"add event <description> /from <start> /to <end>\"");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event with the specified description, completion state, start, and end period.
     *
     * @param description Description of the Event
     * @param isDone boolean value that determines if the initialised Event is completed or not
     * @param from Starting period of the Event
     * @param to Ending period of the Event
     * @throws MeanDukeException if starting or ending period is invalid
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, TYPE_SYMBOL, isDone);
        this.from = from;
        this.to = to;
    }

    public String saveString() {
        return "EVENT" + "\n" + super.saveString() + "\n" + this.from + "\n" + this.to;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.from + " - " + this.to + ")";
    }
}
