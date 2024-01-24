public class Event extends Task{

    protected String start;
    protected String end;

    Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[E]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (from: %s to: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name, this.start, this.end);
    }
}
