public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String parsedFormatToSave() {
        return String.format("E | %c | %s | %s | %s",
                this.isDone ? 'y' : 'n', this.description, this.from, this.to);
    }
}
