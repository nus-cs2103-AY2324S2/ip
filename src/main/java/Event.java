public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
    }

    public Event(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description;
    }
}

