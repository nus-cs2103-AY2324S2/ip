package virtue;

public class Event extends VirtueTask {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String fileFormat() {
        return "E | " + super.fileFormat() + " | " + from + " | " + to;
    }
}