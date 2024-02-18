package tasks;


/**
 * This class represents an Event task by encapsulating information about a specific event,
 * including the description, start and end timings.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Returns an Event object as the public constructor for this class.
     *
     * @param taskName Description of event.
     * @param from Start date or time of the event.
     * @param to end date or time of the event.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats the details of the Event object as a String to be written to a text file.
     *
     * @return A String containing the description and start and end timings of an Event object.
     */
    @Override
    public String saveFormat() {
        int check = this.isCompleted ? 1 : 0;
        assert check == 1 || check == 0 : "check should return 1 or 0";
        return String.format("E | %d | %s | %s | %s\n", check, this.taskName, this.from, this.to);
    }
    @Override
    public String toString() {
        return String.format("[E]%s (From: %s To: %s)", super.toString(), this.from, this.to);
    }
}
