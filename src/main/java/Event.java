public class Event extends Task {

    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(), this.description, this.start, this.end);
    }
    @Override
    public String getSaveLine() {
        return "E " + (this.isDone ? "1 " : "0 ") + this.description + " /from " + this.start + " /to " + this.end + "\n";
    }
}
