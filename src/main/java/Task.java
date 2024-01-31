import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public LocalDateTime parseDateTime(String input) {
        DateTimeFormatter[] formattersWithTime = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
        };

        DateTimeFormatter[] formattersWithoutTime = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        };

        for (DateTimeFormatter formatter : formattersWithTime) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        for (DateTimeFormatter formatter : formattersWithoutTime) {
            try {
                return LocalDate.parse(input, formatter).atTime(0, 0);
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        throw new DateTimeParseException("Invalid date/time format", input, 0);
    }


        public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String fileSavingString();
}
