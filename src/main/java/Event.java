public class Event extends Task {
    protected String from, to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String serializeToCommand(int taskIndex) {
        return "event " + description + " /from " + from + " /to " + to + "\n" + serializeDoneMark(taskIndex);
    }
}
