package chatbot;

import java.time.LocalDateTime;
public class Deadline extends chatbot.Task {
    protected LocalDateTime by;

    public Deadline(String description, java.time.LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public java.time.LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public String toString() {
        String formattedBy = by.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
    }
}
