package martin;

public class Event extends Task {
    private String from;
    private String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileString() {
        String taskStatus = this.getIsDone() ? "1" : "0";
        return "E | " + taskStatus + " | " + this.getDescription() + " | " + this.from + "-" + this.to;
    }
}