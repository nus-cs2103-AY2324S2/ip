import java.io.Serializable;

public class Events extends Task implements Serializable {

    protected String start;
    protected String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return "E ";
    }

    @Override
    public String getDescription() {
        return this.description + " | " + start + " " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to:" + end + ")";
    }
}
