package friendlytool.task;

import friendlytool.process.Date;

/**
 * Class for managing Event tasks.
 */
public class Event extends Task {
    private Date from;
    private Date to;


    /**
     * Constructs a Event.
     *
     * @param name   name of the task.
     * @param isDone completed or not.
     * @param from   the start date.
     * @param to     the end date.
     */
    public Event(String name, boolean isDone, Date from, Date to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts an event to a readable format.
     *
     * @return string format of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts an event to a save format.
     *
     * @return save format of an event.
     */
    @Override
    public String toSaveFormat() {
        return "E " + super.toSaveFormat() + " | " + from.toSaveFormat() + " | " + to.toSaveFormat() + "\n";
    }

    @Override
    public Date getEndTime() {
        return this.to;
    }
}
