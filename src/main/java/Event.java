public class Event extends Task{
    private final String start;
    private final String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatus() + "] " + this.desc + " (from: " + this.start + " to: " + this.end + ")";
    }
}
