import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime deadline;

    private static final DateTimePrinter dateTimePrinter = new DateTimePrinter();

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline);
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimePrinter.print(this.deadline) + ")";
    }
}
