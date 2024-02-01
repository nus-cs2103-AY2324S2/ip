package duke;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + from +" "+ to.trim() + ")";
    }

    public String getFromTo() {
        return from +" "+ to.trim();
    }
}
