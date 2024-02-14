import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task, which is a type of task that needs to be
 * completed.
 * Inherits from the Task class.
 */
class Deadline extends Task {
    private LocalDateTime by;
    public String[] dateFormats = { "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", "dd/MM/yyyy", "MM/dd/yyyy",
            "yyyy/MM/dd", "dd MMM yyyy", "MMM dd yyyy", "yyyy MMM dd", "dd MMM yyyy", "yyyy-MM-d", "d-MM-yyyy",
            "MM-d-yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy/MM/d", "d MMM yyyy", "MMM d yyyy", "yyyy MMM d",
            "d MMM yyyy" };
    public String[] timeFormats = { "HH:mm:ss", "HH:mm", "h:mm a", "HHmm", "hh:mm:ss a" };

    Deadline(String name, LocalDateTime by) throws DukeException {
        super(name);
        this.by = by;
        if (name == null || name.isEmpty()) {
            throw new DukeException("Task name cannot be empty");
        } else if (by == null) {
            throw new DukeException("Invalid date format: Deadline dates should be in dd/mm/yyyy HHmm");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), by.format(formatter));
    }
}