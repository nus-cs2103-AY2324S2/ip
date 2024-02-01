package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    @Override
    public String toString() {
        return String.format("[Deadline]%s (by: %s)", super.toString(), this.deadline.format(outputFormatter));
    }

    @Override
    public String fileString() {
        return String.format("DEADLINE | %s | %s", super.fileString(), this.deadline.format(inputFormatter));
    }
}
