import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {

    protected LocalDateTime byDateTime;
    public Deadline(String description, String by) throws JamieException{
        super(description);
        this.byDateTime = parseDateTime(by);
    }

    public Deadline(String description, String by, boolean isDone) throws JamieException{
        super(description, isDone);
        this.byDateTime = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) throws JamieException {
        // Assuming that the date/time format is "dd/MM/yyyy HHmm"
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new JamieException("Invaild date format. Please use 'dd/MM/yyyy HHmm'.");
        }
    }

    public LocalDateTime getBy() {
        return this.byDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}