import java.util.Objects;

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

    @Override
    public String toDBString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "T|" + display + "|" + this.description + "|" + this.start + "|" + this.end;
    }
}