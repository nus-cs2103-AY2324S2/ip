public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (From: %s To: %s)", super.toString(), this.from, this.to);
    }
}
