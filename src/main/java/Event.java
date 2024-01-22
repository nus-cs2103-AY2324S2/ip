public class Event extends Action {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class
     * @param description action to be made
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @return task output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + "to:" + this.to;
    }
}
