public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescriptionStatus() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String[] getFields() {
        String[] result = new String[4];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        result[2] = this.to;
        result[3] = this.from;
        return result;
    }
}
