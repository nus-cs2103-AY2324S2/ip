package TheAdvisor;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Events extends Task implements Serializable {
    protected final LocalDateTime start;
    protected final LocalDateTime end;

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