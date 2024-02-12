package tyler.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime end;
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    public Deadline(String name, LocalDateTime end) {
        super(name);
        this.end = end;
    }

    public Deadline(String name, LocalDateTime end, boolean isDone) {
        super(name);
        this.end = end;
        this.isDone = isDone;
    }

    @Override
    public String saveToFileString() {
        return "D | " + super.saveToFileString() + " | " +  this.end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[D]" + str + " (by: " + this.end.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
