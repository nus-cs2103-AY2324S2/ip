public class Event extends Task {
    String type = "[E]";
    String start;
    String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description + "(from: " + this.start + "to: " + this.end.replaceAll("\\s+$", "") + ")";
    }
}