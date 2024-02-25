package chatbot;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    /**
     * Constructs a deadline task.
     * @param description The description of the deadline.
     * @param by The date and time of the deadline.
     */
    public Deadline(String description, java.time.LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public String toString() {
        String formattedBy = by.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
    }

    /**
     * Checks if the date is the same as the deadline.
     * @param date The date to be checked.
     * @return True if the date is the same as the deadline, false otherwise.
     */
    public Boolean isDueDate(LocalDateTime date) {
        return this.by.equals(date);
    }

}
