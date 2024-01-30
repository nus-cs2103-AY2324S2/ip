package TheAdvisor;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Events extends Task implements Serializable {

//    protected String start;
//    protected String end;

    private final LocalDateTime start;
    private final LocalDateTime end;

    public Events(String description, LocalDateTime start, LocalDateTime end) {
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
        return "[E]" + super.toString() + " (from: " + start.format(Task.outputFormat) + "hrs to: " +
                end.format(Task.outputFormat) + "hrs)";
    }
}