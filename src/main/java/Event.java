public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[D][" + statusIcon + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}
