public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = TaskType.EVENT;
    }

    @Override
    public String toString() {
        return type.getSymbol() + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}