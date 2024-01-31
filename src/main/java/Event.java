public class Event extends Task {
    protected String from;
    protected String to;
    static protected String ALIAS = "E";

    public Event(String taskName, boolean status, String from, String to) {
        super(taskName, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStore() {
        return ALIAS + super.toStore() + "," + from + "," + to;
    }
}
