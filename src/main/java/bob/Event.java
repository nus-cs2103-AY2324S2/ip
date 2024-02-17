package bob;

/*
 * This class represents an event that can be recorded in the tasklist.
 */
class Event extends Task {
    protected String from;
    protected String to;

    /*
     * A constructor to create a new event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /*
     * A method to get from.
     *
     * @return The start date of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /*
     * A method to get to.
     *
     * @return The end date of the event.
     */
    public String getTo() {
        return this.to;
    }

    /*
     * A method that returns the status of the task.
     *
     * @return A label [E] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}