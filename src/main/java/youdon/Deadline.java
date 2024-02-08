package youdon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.deadline.format(formatter);
        return "[" + this.getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description + "(by: " + formattedDateTime + ")";
    }
}
