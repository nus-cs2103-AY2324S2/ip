/**
 * Class for task start with event
 */
public class Event extends Task{
    private String from;
    private String to;

    /**
     * constructor
     * @param descrip the dicription of the task.
     * @param from from field.
     * @param to to field.
     */
    public Event(String descrip, String from, String to) {
        super(descrip);
        this.from = from;
        this.to = to;
    }

    /**
     * Override the abstract class
     * @return T
     */
    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    /**
     * Override the abstract class
     * @return event
     */
    @Override
    public String getTaskType() {
        return "event";
    }
    /**
     * Override the toString method
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s(from: %sto: %s)",super.toString(), this.from, this.to);
    }

    /**
     * return from
     * @return from
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * return to
     * @return to
     */
    public String getTo() {
        return this.to;
    }
}
