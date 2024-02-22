package sam.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime date;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
 public Deadline(String description, String by) {
        super(description);
        this.date = LocalDateTime.parse(by, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, this.description, date.format(INPUT_FORMATTER));
    }
}
