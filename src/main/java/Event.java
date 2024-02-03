import java.io.Serializable;

public class Event extends Task implements Serializable {
    private String start;
    private String end;
    private static final long serialVersionUID = 4L;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + "[" + (isDone ? "X" : " ") + "] " + description
                + "(from: " + start
                + "to: "+ end + ")";
    }
}
