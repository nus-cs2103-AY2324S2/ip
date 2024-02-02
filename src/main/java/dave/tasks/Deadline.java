package dave.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = LocalDateTime.parse(deadline, FORMATTER_INPUT);
    }

    @Override
    public String toString() {
        return String.format("[Deadline]%s (by: %s)", super.toString(), this.deadline.format(FORMATTER_OUTPUT));
    }

    @Override
    public String fileString() {
        return String.format("DEADLINE | %s | %s", super.fileString(), this.deadline.format(FORMATTER_INPUT));
    }
}
