package lex.tasks;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }
}
