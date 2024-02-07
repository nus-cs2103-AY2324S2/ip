package lia;

public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription() +
                " (from: " + start + " to: " + end + ")";
    }
}
