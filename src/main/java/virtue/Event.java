package virtue;

/** Represents an event task. */
public class Event extends VirtueTask {
    private VirtueDateTime from;
    private VirtueDateTime to;

    public Event(String description, VirtueDateTime from, VirtueDateTime to) {
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
        return "E | " + super.fileFormat() + " | " + from.fileFormat() + " | " + to.fileFormat();
    }
}