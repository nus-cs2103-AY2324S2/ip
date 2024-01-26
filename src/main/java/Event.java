public class Event extends Task {
    String from;
    String to;
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getRep() {
        return String.format("[E]%s (from: %s to: %s)", super.getRep(), this.from, this.to);
    }
}
