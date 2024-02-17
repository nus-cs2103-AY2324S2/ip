package reacher.task;

import java.time.LocalDate;

/**
 * Task with start and end date.
 */
public class Events extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Events(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + " to:" + end + ")";
    }
}
