public class Event extends Task {

    protected String from;
    protected String to;


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescriptionStatus() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from: " + this.from + " to: " + this.to + ")";
    }
}
