package tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime actualStart;
    private LocalDateTime actualEnd;

    public Events(String description, LocalDateTime actualStart, LocalDateTime actualEnd) {
        super(description);
        this.actualStart = actualStart;
        this.actualEnd = actualEnd;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm'hrs'");
        if (this.isMarked()) {
            return "[E][X] " + super.toString() + "(from: " + this.actualStart.format(outputFormatter) + 
            " to: " + this.actualEnd.format(outputFormatter) + ")";
        } else {
            return "[E][ ] " + super.toString() + "(from: " + this.actualStart.format(outputFormatter) + 
            ", to: " + this.actualEnd.format(outputFormatter) + ")";
        }
    }
    @Override
    public String identifier() {
        return "[E]";
    }

    @Override
    public LocalDate getDeadline() {
        return null;
    }
}