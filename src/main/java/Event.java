public class Event extends Task{
    private String from;
    private String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String getDescription() {
        return String.format("%s (from: %s to: %s)", this.description, this.from, this.to);
    }

    public String getCommand() {
        return String.format("event %s /from %s /to %s\n%b\n", this.description, this.from, this.to, this.isDone);
    }
}
