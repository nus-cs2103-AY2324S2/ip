package pingmebot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected String description;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.description = description;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + ")";
    }

    public String updateDeadlineText(int isCompleted) {
        String text = "";
        text += "deadline | " + isCompleted + " | " + this.description + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Deadline otherDeadline = (Deadline) obj;
        return this.description.equals(otherDeadline.description) && this.by.equals(otherDeadline.by);
    }
}
