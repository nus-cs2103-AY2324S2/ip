package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, boolean status, LocalDateTime deadline) {
        super(description, status, Type.DEADLINE);
        this.deadline = deadline;
    }

    public String getDeadline() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        return formattedDeadline;
    }
}
