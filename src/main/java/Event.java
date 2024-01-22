public class Event extends Task{
    private String from;
    private String to;
    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    public String getFrom() {
        return this.from;
    }
    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from " + this.getFrom() + " to " + this.getTo() + ")";
    }


}
