package tasks;

public class Event extends Task {
    public static final String EVENT_ICON = "E";
    private String from;
    private String to;

    Event(String description, String from, String to) {  // default access modifier
        super(description);
        this.from = from;
        this.to = to;
    }
    
    Event(String description, boolean isDone, String from, String to) {  // default access modifier
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return EVENT_ICON;
    }
    
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String getFrom() {
        return from;
    }
    
    public String getTo() {
        return to;
    }
}
